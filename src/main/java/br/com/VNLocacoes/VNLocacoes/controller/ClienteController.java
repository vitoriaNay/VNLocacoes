package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.entity.ClienteEntity;
import br.com.VNLocacoes.VNLocacoes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // COMBINA AS ANOTAÇÕES DE @Controller e @ResponseBody, ÚTIL PARA DESENVOLVER SERVIÇOS RESTful
@RequestMapping("/cliente") // MAPEIA AS SOLICITAÇÕES DA WEB QUE SERÃO ATENDIDAS POR ESTE CONTROLADOR
public class ClienteController {

    @Autowired // ESPECIFICA QUE ESTA DEPENDÊNCIA DEVERÁ SER INJETADA PELO SPRING BOOT
    private ClienteService clienteService;

    @PostMapping // DEFINE O VERBO HTTP QUE SERÁ ATENDIDO POR ESTE MÉTODO (PostMapping, GetMapping, PutMapping, DeleteMapping)
    public ResponseEntity<ClienteEntity> salvarCliente(@RequestBody ClienteEntity cliente) {
        var clienteSalvo = clienteService.salvarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> listarTodosOsClientes() {
        var listaDeClientes = clienteService.listarTodosOsClientes();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaDeClientes);
    }
}
