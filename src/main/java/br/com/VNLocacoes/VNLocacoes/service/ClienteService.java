package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.entity.ClienteEntity;
import br.com.VNLocacoes.VNLocacoes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
