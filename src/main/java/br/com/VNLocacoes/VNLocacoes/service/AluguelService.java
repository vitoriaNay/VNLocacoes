package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.AluguelDTO;
import br.com.VNLocacoes.VNLocacoes.dto.CarroDTO;
import br.com.VNLocacoes.VNLocacoes.dto.ClienteDTO;
import br.com.VNLocacoes.VNLocacoes.dto.PagamentoDTO;
import br.com.VNLocacoes.VNLocacoes.entity.AluguelEntity;
import br.com.VNLocacoes.VNLocacoes.entity.PagamentoEntity;
import br.com.VNLocacoes.VNLocacoes.entity.StatusAluguel;
import br.com.VNLocacoes.VNLocacoes.entity.UsuarioEntity;
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

    public AluguelDTO salvarAluguel(AluguelDTO aluguel) {
        try {
            aluguel.setStatus(StatusAluguel.ATIVO);

            PagamentoDTO pagamentoDTO = pagamentoService.salvarPagamento(PagamentoMapper.INSTANCE.toDTO(aluguel.getPagamento()));
            aluguel.setPagamento(PagamentoMapper.INSTANCE.toEntity(pagamentoDTO));

            ClienteDTO clienteDTO = clienteService.buscarClientePorId(aluguel.getCliente().getId());
            aluguel.setCliente(ClienteMapper.INSTANCE.toEntity(clienteDTO));

            CarroDTO carroDTO = carroService.buscarCarroPorPlaca(aluguel.getCarro().getPlaca());
            // VERIFICA A DISPONIBILIDADE DO CARRO QUE ESTÁ TENTANDO SER ALUGADO
            if (!carroDTO.isDisponibilidade()) {
                // SE NÃO ESTIVER DISPONÍVEL, LANÇA UMA EXCEPTION
                throw new CarroNaoDisponivelExcecao();
            }

            aluguel.setCarro(CarroMapper.INSTANCE.toEntity(carroDTO));


            // OBTENDO OS DADOS DO USUÁRIO LOGADO
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            UsuarioEntity usuarioInfo = (UsuarioEntity) auth.getPrincipal();
            UserDetails usuario = usuarioRepository.findByLogin(usuarioInfo.getLogin());
            aluguel.setUsuario((UsuarioEntity) usuario);

            AluguelEntity aluguelSalvo = aluguelRepository.save(AluguelMapper.INSTANCE.toEntity(aluguel));
            // APÓS REALIZAR O ALUGUEL, MUDA A DISPONIBILIDADE DO CARRO
            carroService.alterarDisponibilidade(aluguelSalvo.getCarro().getId());

            return AluguelMapper.INSTANCE.toDTO(aluguelSalvo);
        } catch (CarroNaoDisponivelExcecao e) {
            throw new CarroNaoDisponivelExcecao();
        }
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
            aluguel.setStatus(StatusAluguel.ATIVO);
        }

        return AluguelMapper.INSTANCE.toDTO(aluguelRepository.save(aluguel));
    }
}
