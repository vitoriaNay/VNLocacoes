package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.ClienteDTO;
import br.com.VNLocacoes.VNLocacoes.entity.ClienteEntity;
import br.com.VNLocacoes.VNLocacoes.exception.RegistroNaoEncontradoExcecao;
import br.com.VNLocacoes.VNLocacoes.mapper.ClienteMapper;
import br.com.VNLocacoes.VNLocacoes.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // ESPECIFICA QUE ESTA CLASSE IRÁ FORNECER SERVIÇOS A ALGUM CONTROLADOR
public class ClienteService {

    @Autowired // ESPCIFICA QUE ESTA DEPENDÊNCIA DEVERÁ SER INJETADA PELO SPRING BOOT
    private ClienteRepository clienteRepository;

    public ClienteDTO salvarCliente(ClienteDTO cliente) {
        var clienteSalvo = clienteRepository.save(ClienteMapper.INSTANCE.toEntity(cliente));

        return ClienteMapper.INSTANCE.toDTO(clienteSalvo);
    }

    public List<ClienteDTO> buscarTodosOsClientes() {
       List<ClienteDTO> listaClientes = new ArrayList<ClienteDTO>();

       clienteRepository.findAll().forEach((cliente) -> listaClientes.add(ClienteMapper.INSTANCE.toDTO(cliente)));

       return listaClientes;
    }

    public ClienteDTO buscarClientePorId(Long id) {
         Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);

         if (clienteOptional.isEmpty()) {
             throw new RegistroNaoEncontradoExcecao("Registro não encontrado na base de dados");
         }

         return ClienteMapper.INSTANCE.toDTO(clienteOptional.get());
    }

    public ClienteDTO atualizarDadosDoCliente(Long id, ClienteDTO cliente) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao("Registro não encontrado na base de dados");
        }

        ClienteEntity clienteAtualizado = new ClienteEntity();
        BeanUtils.copyProperties(ClienteMapper.INSTANCE.toEntity(cliente), clienteAtualizado);

        clienteAtualizado.setId(clienteOptional.get().getId());

        clienteRepository.save(clienteAtualizado);

        return ClienteMapper.INSTANCE.toDTO(clienteAtualizado);
    }

    public boolean deletarCliente(Long id) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao("Registro não encontrado na base de dados");
        }

        clienteRepository.deleteById(id);

        return true;
    }
}
