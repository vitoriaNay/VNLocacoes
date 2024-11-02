package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.dto.ClienteDTO;
import br.com.VNLocacoes.VNLocacoes.entity.ClienteEntity;
import br.com.VNLocacoes.VNLocacoes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // COMBINA AS ANOTAÇÕES DE @Controller e @ResponseBody, ÚTIL PARA DESENVOLVER SERVIÇOS RESTful
@RequestMapping("/cliente") // MAPEIA AS SOLICITAÇÕES DA WEB QUE SERÃO ATENDIDAS POR ESTE CONTROLADOR
public class ClienteController {

    @Autowired // ESPECIFICA QUE ESTA DEPENDÊNCIA DEVERÁ SER INJETADA PELO SPRING BOOT
    private ClienteService clienteService;

    @PostMapping // DEFINE O VERBO HTTP QUE SERÁ ATENDIDO POR ESTE MÉTODO (PostMapping, GetMapping, PutMapping, DeleteMapping)
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody @Valid ClienteDTO cliente) {
        ClienteDTO clienteSalvo = clienteService.salvarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodosOsClientes() {
        var listaDeClientes = clienteService.buscarTodosOsClientes();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaDeClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable(name = "id") Long id) {
        ClienteDTO clienteExistente = clienteService.buscarClientePorId(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteExistente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarDadosDoCLiente(@PathVariable(value = "id") Long id, @RequestBody @Valid ClienteDTO cliente) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteService.atualizarDadosDoCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable(name = "id") Long id) {
        boolean statusDelecao = clienteService.deletarCliente(id);

        if (statusDelecao) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Cliente excluído com sucesso");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Houve algum erro na requisição, tente novamente mais tarde");
    }
}
