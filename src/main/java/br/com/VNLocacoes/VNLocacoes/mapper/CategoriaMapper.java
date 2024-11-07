package br.com.VNLocacoes.VNLocacoes.mapper;


import br.com.VNLocacoes.VNLocacoes.dto.CategoriaDTO;
import br.com.VNLocacoes.VNLocacoes.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaEntity toEntity(CategoriaDTO categoriaDTO);

    CategoriaDTO toDTO(CategoriaEntity categoriaEntity);
}
