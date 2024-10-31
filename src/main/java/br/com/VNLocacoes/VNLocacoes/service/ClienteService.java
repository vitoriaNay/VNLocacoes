package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.entity.ClienteEntity;
import br.com.VNLocacoes.VNLocacoes.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // ESPECIFICA QUE ESTA CLASSE IRÁ FORNECER SERVIÇOS A ALGUM CONTROLADOR
public class ClienteService {

    @Autowired // ESPCIFICA QUE ESTA DEPENDÊNCIA DEVERÁ SER INJETADA PELO SPRING BOOT
    private ClienteRepository clienteRepository;

    public ClienteEntity salvarCliente(ClienteEntity cliente) {
        var clienteSalvo = clienteRepository.save(cliente);

        return clienteSalvo;
    }

    public List<ClienteEntity> listarTodosOsClientes() {
        var listaDeClientes = clienteRepository.findAll();

        return listaDeClientes;
    }

    public Optional<ClienteEntity> atualizarDadosDoCliente(Long id, ClienteEntity cliente) {
        Optional<ClienteEntity> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isEmpty()) {
            return clienteExistente;
        }

        ClienteEntity clienteAtualizado = clienteExistente.get();
        clienteAtualizado.setNome(cliente.getNome());
        clienteAtualizado.setEmail(cliente.getEmail());
        clienteAtualizado.setTelefone(cliente.getTelefone());

        clienteRepository.save(clienteAtualizado);
        BeanUtils.copyProperties(clienteAtualizado, clienteExistente);

        return clienteExistente;
    }
}
