package br.com.VNLocacoes.VNLocacoes.controller;


import br.com.VNLocacoes.VNLocacoes.dto.CategoriaDTO;
import br.com.VNLocacoes.VNLocacoes.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/servicos/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDTO> salvarCategoria(@RequestBody @Valid CategoriaDTO categoria) {
        CategoriaDTO categoriaSalva = categoriaService.salvarCategoria(categoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaSalva);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodasAsCategorias() {
         List<CategoriaDTO> listaCategorias = categoriaService.listarTodasAsCategorias();

         return ResponseEntity.status(HttpStatus.OK)
                 .body(listaCategorias);
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoriaDTO> buscarCategoriaPorId(@PathVariable(name = "id") Long id) {
        CategoriaDTO categoriaExistente = categoriaService.buscarCategoriaPorId(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoriaExistente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarDadosDaCategoria(@PathVariable(name = "id") Long id, @RequestBody @Valid CategoriaDTO categoria) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoriaService.atualizarDadosDaCategoria(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable(name = "id") Long id) {
        boolean statusDelecao = categoriaService.deletarCategoria(id);

        if (statusDelecao) {
            // TESTE DE RESPONSE DA API
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("mensagem", "Categoria deletada com sucesso");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Houve algum erro na requisição, tente novamente mais tarde");
    }

    @GetMapping("/busca")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorNome(@RequestParam(name = "nome") String nome) {
        CategoriaDTO categoriaExistente = categoriaService.buscarCategoriaPorNome(nome);

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoriaExistente);
    }
}
