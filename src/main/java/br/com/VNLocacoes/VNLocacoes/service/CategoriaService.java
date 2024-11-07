package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.CategoriaDTO;
import br.com.VNLocacoes.VNLocacoes.entity.CategoriaEntity;
import br.com.VNLocacoes.VNLocacoes.exception.RegistroNaoEncontradoExcecao;
import br.com.VNLocacoes.VNLocacoes.repository.CategoriaRepository;
import br.com.VNLocacoes.VNLocacoes.mapper.CategoriaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaDTO salvarCategoria(CategoriaDTO categoria) {
        CategoriaEntity categoriaSalva = categoriaRepository.save(CategoriaMapper.INSTANCE.toEntity(categoria));

        return CategoriaMapper.INSTANCE.toDTO(categoriaSalva);
    }

    public List<CategoriaDTO> listarTodasAsCategorias() {
        List<CategoriaDTO> listaCategorias = new ArrayList<>();

        categoriaRepository.findAll().forEach((categoria) -> listaCategorias.add(CategoriaMapper.INSTANCE.toDTO(categoria)));

        return listaCategorias;
    }

    public CategoriaDTO buscarCategoriaPorId(Long id) {
        Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao();
        }

        return CategoriaMapper.INSTANCE.toDTO(categoriaOptional.get());
    }

    public CategoriaDTO atualizarDadosDaCategoria(Long id, CategoriaDTO categoria) {
        Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao();
        }

        CategoriaEntity categoriaAtualizada = new CategoriaEntity();

        BeanUtils.copyProperties(categoria, categoriaAtualizada);

        categoriaAtualizada.setId(categoriaOptional.get().getId());

        categoriaRepository.save(categoriaAtualizada);

        return CategoriaMapper.INSTANCE.toDTO(categoriaAtualizada);
    }

    public boolean deletarCategoria(Long id) {
        Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isEmpty()) {
            throw new RegistroNaoEncontradoExcecao();
        }

        categoriaRepository.deleteById(id);

        return true;
    }
}
