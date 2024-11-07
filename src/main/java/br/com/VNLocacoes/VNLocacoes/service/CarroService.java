package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepositor;
}
