package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.MarcaDTO;
import br.com.VNLocacoes.VNLocacoes.entity.MarcaEntity;
import br.com.VNLocacoes.VNLocacoes.exception.RegistroNaoEncontradoExcecao;
import br.com.VNLocacoes.VNLocacoes.mapper.MarcaMapper;
import br.com.VNLocacoes.VNLocacoes.repository.MarcaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    public MarcaDTO salvarMarca(MarcaDTO marca) {
        MarcaEntity marcaSalva = marcaRepository.save(MarcaMapper.INSTANCE.toEntity(marca));

        return MarcaMapper.INSTANCE.toDTO(marcaSalva);
    }

    public List<MarcaDTO> listarTodasAsMarcas() {
        List<MarcaDTO> listaMarcas = new ArrayList<>();

        marcaRepository.findAll().forEach((marca) -> listaMarcas.add(MarcaMapper.INSTANCE.toDTO(marca)));

        return listaMarcas;
    }

    public MarcaDTO buscarMarcaPorId(Long id) {
        Optional<MarcaEntity> marcaOptional = marcaRepository.findById(id);

        if (marcaOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao();
        }

        return MarcaMapper.INSTANCE.toDTO(marcaOptional.get());
    }

    public MarcaDTO atualizarDadosDaMarca(Long id, MarcaDTO marca) {
        Optional<MarcaEntity> marcaOptional = marcaRepository.findById(id);

        if (marcaOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao();
        }

        MarcaEntity marcaAtualizada = new MarcaEntity();

        BeanUtils.copyProperties(marca, marcaAtualizada);;

        marcaAtualizada.setId(marcaOptional.get().getId());

        marcaRepository.save(marcaAtualizada);

        return MarcaMapper.INSTANCE.toDTO(marcaAtualizada);
    }

    public boolean deletarMarca(Long id) {
        Optional<MarcaEntity> marcaOptional = marcaRepository.findById(id);

        if (marcaOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao();
        }

        marcaRepository.deleteById(id);

        return true;
    }
}
