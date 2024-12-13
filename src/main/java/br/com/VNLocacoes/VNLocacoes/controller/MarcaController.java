package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.dto.MarcaDTO;
import br.com.VNLocacoes.VNLocacoes.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/servicos/marca")
@CrossOrigin(origins = "http://localhost:3000")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaDTO> salvarMarca(@RequestBody @Valid MarcaDTO categoria) {
        MarcaDTO categoriaSalva = marcaService.salvarMarca(categoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaSalva);
    }

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> listarTodasAsMarcas() {
        List<MarcaDTO> listaMarcas = marcaService.listarTodasAsMarcas();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaMarcas);
    }

    @GetMapping("/{id}")
    ResponseEntity<MarcaDTO> buscarMarcaPorId(@PathVariable(name = "id") Long id) {
        MarcaDTO marcaExistente = marcaService.buscarMarcaPorId(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(marcaExistente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> atualizarDadosDaMarca(@PathVariable(name = "id") Long id, @RequestBody @Valid MarcaDTO categoria) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(marcaService.atualizarDadosDaMarca(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMarca(@PathVariable(name = "id") Long id) {
        boolean statusDelecao = marcaService.deletarMarca(id);

        if (statusDelecao) {
            // TESTE DE RESPONSE DA API
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("mensagem", "Marca deletada com sucesso");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Houve algum erro na requisição, tente novamente mais tarde");
    }

    @GetMapping("/busca")
    public ResponseEntity<MarcaDTO> buscarMarcaPorNome(@RequestParam(name = "nome") String nome) {
        MarcaDTO marcaExistente = marcaService.buscarMarcaPorNome(nome);

        return ResponseEntity.status(HttpStatus.OK)
                .body(marcaExistente);
    }
}
