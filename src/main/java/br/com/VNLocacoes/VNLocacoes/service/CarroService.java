package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.CarroDTO;
import br.com.VNLocacoes.VNLocacoes.dto.CategoriaDTO;
import br.com.VNLocacoes.VNLocacoes.dto.MarcaDTO;
import br.com.VNLocacoes.VNLocacoes.entity.CarroEntity;
import br.com.VNLocacoes.VNLocacoes.entity.CategoriaEntity;
import br.com.VNLocacoes.VNLocacoes.exception.RegistroJaExisteExcecao;
import br.com.VNLocacoes.VNLocacoes.exception.RegistroNaoEncontradoExcecao;
import br.com.VNLocacoes.VNLocacoes.mapper.CarroMapper;
import br.com.VNLocacoes.VNLocacoes.mapper.CategoriaMapper;
import br.com.VNLocacoes.VNLocacoes.mapper.MarcaMapper;
import br.com.VNLocacoes.VNLocacoes.repository.CarroRepository;
import br.com.VNLocacoes.VNLocacoes.repository.CategoriaRepository;
import br.com.VNLocacoes.VNLocacoes.repository.MarcaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        Optional<CarroEntity> carroOptional = carroRepository.findByPlaca(carro.getPlaca());
        if (carroOptional.isEmpty()){// VERIFICA SE A CATEGORIA INFORMADA EXISTE NO BANCO DE DADOS
            CategoriaDTO categoria = categoriaService.buscarCategoriaPorNome(carro.getCategoria().getNome());
            // VERIFICA SE A MARCA INFORMADA EXISTE NO BANCO DE DADOS
            MarcaDTO marca = marcaService.buscarMarcaPorNome(carro.getMarca().getNome());

            carro.setCategoria(CategoriaMapper.INSTANCE.toEntity(categoria));
            carro.setMarca(MarcaMapper.INSTANCE.toEntity(marca));

            CarroEntity carroSalvo = carroRepository.save(CarroMapper.INSTANCE.toEntity(carro));

            return CarroMapper.INSTANCE.toDTO(carroSalvo);
        } else {
            throw new RegistroJaExisteExcecao("Já existe um carro registrado com a placa informada");
        }
    }

    public List<CarroDTO> listarTodosOsCarros() {
        List<CarroDTO> listaCarros = new ArrayList<>();

        carroRepository.findAll().forEach((carro) -> listaCarros.add(CarroMapper.INSTANCE.toDTO(carro)));

        return listaCarros;
    }

    public List<CarroDTO> listarCarrosPorDisponibilidade(boolean disponibilidade) {
        List<CarroDTO> listaCarros = new ArrayList<>();

        carroRepository.findByDisponibilidade(disponibilidade)
                .forEach((carro) -> listaCarros.add(CarroMapper.INSTANCE.toDTO(carro)));

        return listaCarros;
    }

    public CarroDTO buscarCarroPorPlaca(String placa) {
        Optional<CarroEntity> carroOptional = carroRepository.findByPlaca(placa);

        if (carroOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao("Carro não encontrado na base de dados");
        }

        return CarroMapper.INSTANCE.toDTO(carroOptional.get());
    }


    public CarroDTO atualizarDadosDoCarro(Long id, CarroDTO carro) {
        Optional<CarroEntity> carroOptional = carroRepository.findById(id);

        if (carroOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao("Carro não encontrado na base de dados");
        }

        CarroEntity carroAtualizado = new CarroEntity();
        BeanUtils.copyProperties(carro, carroAtualizado);
        carroAtualizado.setId(carroOptional.get().getId());
        carroAtualizado.setCategoria(carroOptional.get().getCategoria());
        carroAtualizado.setMarca(carroOptional.get().getMarca());

        carroRepository.save(carroAtualizado);


        return CarroMapper.INSTANCE.toDTO(carroAtualizado);
    }

    public CarroDTO alterarDisponibilidade(Long id) {
        Optional<CarroEntity> carroOptional = carroRepository.findById(id);

        if (carroOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao("Carro não encontrado na base de dados");
        }

        CarroEntity carroExistente = carroOptional.get();

        if (carroExistente.isDisponibilidade()) {
            carroExistente.setDisponibilidade(false);
        } else {
            carroExistente.setDisponibilidade(true);
        }

        carroRepository.save(carroExistente);

        return CarroMapper.INSTANCE.toDTO(carroExistente);
    }

    public boolean deletarCarro(Long id) {
        Optional<CarroEntity> carroOptional = carroRepository.findById(id);

        if (carroOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao("Carro não encontrado na base de dados");
        }

        carroRepository.deleteById(id);

        return true;
    }

}
