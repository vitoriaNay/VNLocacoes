package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.CarroDTO;
import br.com.VNLocacoes.VNLocacoes.dto.CategoriaDTO;
import br.com.VNLocacoes.VNLocacoes.dto.MarcaDTO;
import br.com.VNLocacoes.VNLocacoes.entity.CarroEntity;
import br.com.VNLocacoes.VNLocacoes.entity.CategoriaEntity;
import br.com.VNLocacoes.VNLocacoes.mapper.CarroMapper;
import br.com.VNLocacoes.VNLocacoes.mapper.CategoriaMapper;
import br.com.VNLocacoes.VNLocacoes.mapper.MarcaMapper;
import br.com.VNLocacoes.VNLocacoes.repository.CarroRepository;
import br.com.VNLocacoes.VNLocacoes.repository.CategoriaRepository;
import br.com.VNLocacoes.VNLocacoes.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaService marcaService;

    public CarroDTO salvarCarro(CarroDTO carro) {
        CategoriaDTO categoria = categoriaService.buscarCategoriaPorNome(carro.getCategoria().getNome());

        MarcaDTO marca = marcaService.buscarMarcaPorNome(carro.getMarca().getNome());

        carro.setCategoria(CategoriaMapper.INSTANCE.toEntity(categoria));
        carro.setMarca(MarcaMapper.INSTANCE.toEntity(marca));

        CarroEntity carroSalvo = carroRepository.save(CarroMapper.INSTANCE.toEntity(carro));

        return CarroMapper.INSTANCE.toDTO(carroSalvo);
    }
}
