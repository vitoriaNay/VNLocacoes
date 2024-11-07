package br.com.VNLocacoes.VNLocacoes.mapper;

import br.com.VNLocacoes.VNLocacoes.dto.MarcaDTO;
import br.com.VNLocacoes.VNLocacoes.entity.MarcaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    MarcaEntity toEntity(MarcaDTO marcaDTO);

    MarcaDTO toDTO(MarcaEntity marcaEntity);
}
