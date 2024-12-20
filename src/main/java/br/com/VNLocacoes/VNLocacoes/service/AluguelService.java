package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.AluguelDTO;
import br.com.VNLocacoes.VNLocacoes.dto.CarroDTO;
import br.com.VNLocacoes.VNLocacoes.dto.ClienteDTO;
import br.com.VNLocacoes.VNLocacoes.dto.PagamentoDTO;
import br.com.VNLocacoes.VNLocacoes.entity.*;
import br.com.VNLocacoes.VNLocacoes.exception.CarroNaoDisponivelExcecao;
import br.com.VNLocacoes.VNLocacoes.exception.RegistroNaoEncontradoExcecao;
import br.com.VNLocacoes.VNLocacoes.mapper.AluguelMapper;
import br.com.VNLocacoes.VNLocacoes.mapper.CarroMapper;
import br.com.VNLocacoes.VNLocacoes.mapper.ClienteMapper;
import br.com.VNLocacoes.VNLocacoes.mapper.PagamentoMapper;
import br.com.VNLocacoes.VNLocacoes.repository.AluguelRepository;
import br.com.VNLocacoes.VNLocacoes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    CarroService carroService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PagamentoMapper pagamentoMapper;

    public AluguelDTO salvarAluguel(AluguelDTO aluguel) {
        try {
            aluguel.setStatus(StatusAluguel.ATIVO);

            PagamentoDTO pagamentoDTO = pagamentoService.salvarPagamento(pagamentoMapper.toDTO(aluguel.getPagamento()));
            aluguel.setPagamento(pagamentoMapper.toEntity(pagamentoDTO));

            ClienteDTO clienteDTO = clienteService.buscarClientePorId(aluguel.getCliente().getId());
            aluguel.setCliente(ClienteMapper.INSTANCE.toEntity(clienteDTO));

            CarroDTO carroDTO = carroService.buscarCarroPorId(aluguel.getCarro().getId());
            // VERIFICA A DISPONIBILIDADE DO CARRO QUE ESTÁ TENTANDO SER ALUGADO
            if (!carroDTO.isDisponibilidade()) {
                // SE NÃO ESTIVER DISPONÍVEL, LANÇA UMA EXCEPTION
                throw new CarroNaoDisponivelExcecao();
            }

            aluguel.setCarro(CarroMapper.INSTANCE.toEntity(carroDTO));

            AluguelEntity aluguelSalvo = aluguelRepository.save(AluguelMapper.INSTANCE.toEntity(aluguel));
            // APÓS REALIZAR O ALUGUEL, MUDA A DISPONIBILIDADE DO CARRO
            carroService.alterarDisponibilidade(aluguelSalvo.getCarro().getId());
            pagamentoDTO.setAluguel(aluguelSalvo);
            pagamentoService.atualizarPagamento(pagamentoDTO);
            return AluguelMapper.INSTANCE.toDTO(aluguelSalvo);
        } catch (CarroNaoDisponivelExcecao e) {
            throw new CarroNaoDisponivelExcecao();
        }
    }

    public List<AluguelDTO> listarTodosOsAlugueis() {
        List<AluguelDTO> listaAlugueis = new ArrayList<>();

        aluguelRepository.findAll().forEach((aluguel) -> listaAlugueis.add(AluguelMapper.INSTANCE.toDTO(aluguel)));

        return listaAlugueis;
    }

    public AluguelDTO alterarStatusDoAluguel(Long id) {
        Optional<AluguelEntity> aluguelOptional = aluguelRepository.findById(id);

        if (aluguelOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao("Aluguel não encontrado na base de dados");
        }

        AluguelEntity aluguel = aluguelOptional.get();

        if (aluguel.getStatus() == StatusAluguel.ATIVO) {
            aluguel.setStatus(StatusAluguel.INATIVO);
        } else {
            // CASO O ALUGUEL TENTE SER REATIVADO, MAS O CARRO ESTIVER INDISPONÍVEL, LANÇA UMA EXCEÇÃO
            if(!aluguel.getCarro().isDisponibilidade()) {
                throw new CarroNaoDisponivelExcecao("Reativar este aluguel não é possível, pois o carro desejado encontra-se em outro aluguel ativo");
            }
            aluguel.setStatus(StatusAluguel.ATIVO);
        }
        // ALTERA A DISPONIBILIDADE DO CARRO UMA VEZ QUE O STATUS DO ALUGUEL É ALTERADO
        CarroEntity carroAluguel = CarroMapper.INSTANCE.toEntity(carroService.alterarDisponibilidade(aluguel.getCarro().getId()));
        aluguel.setCarro(carroAluguel);

        return AluguelMapper.INSTANCE.toDTO(aluguelRepository.save(aluguel));
    }
}
