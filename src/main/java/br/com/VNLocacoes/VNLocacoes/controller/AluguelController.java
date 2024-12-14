package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.dto.AluguelDTO;
import br.com.VNLocacoes.VNLocacoes.service.AluguelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos/aluguel")
@CrossOrigin(origins = "*")
public class AluguelController {

    @Autowired
    AluguelService aluguelService;

    @PostMapping
    public ResponseEntity<AluguelDTO> salvarAluguel(@RequestBody @Valid AluguelDTO aluguel) {
        AluguelDTO aluguelSalvo = aluguelService.salvarAluguel(aluguel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aluguelSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AluguelDTO> alterarStatusDoAluguel(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(aluguelService.alterarStatusDoAluguel(id));
    }
}
