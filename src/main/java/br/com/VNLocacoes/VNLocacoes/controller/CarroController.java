package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;
}
