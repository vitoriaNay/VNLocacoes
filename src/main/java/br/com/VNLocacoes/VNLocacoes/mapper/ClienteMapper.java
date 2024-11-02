package br.com.VNLocacoes.VNLocacoes.mapper;

import br.com.VNLocacoes.VNLocacoes.dto.ClienteDTO;
import br.com.VNLocacoes.VNLocacoes.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDTO(ClienteEntity clienteEntity);

    ClienteEntity toEntity(ClienteDTO clienteDTO);
}
