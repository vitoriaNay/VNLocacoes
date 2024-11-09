package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.dto.CarroDTO;
import br.com.VNLocacoes.VNLocacoes.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroDTO> salvarCarro(@RequestBody @Valid CarroDTO carro) {
        CarroDTO carroSalvo = carroService.salvarCarro(carro);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carroSalvo);
    }
}
