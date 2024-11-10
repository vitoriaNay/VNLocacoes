package br.com.VNLocacoes.VNLocacoes.mapper;

import br.com.VNLocacoes.VNLocacoes.dto.AluguelDTO;
import br.com.VNLocacoes.VNLocacoes.entity.AluguelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AluguelMapper {

    AluguelMapper INSTANCE = Mappers.getMapper(AluguelMapper.class);

    AluguelEntity toEntity(AluguelDTO aluguelDTO);

    AluguelDTO toDTO(AluguelEntity aluguel);
}
