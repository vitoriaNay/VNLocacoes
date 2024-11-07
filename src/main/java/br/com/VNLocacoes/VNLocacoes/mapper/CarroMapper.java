package br.com.VNLocacoes.VNLocacoes.mapper;

import br.com.VNLocacoes.VNLocacoes.dto.CarroDTO;
import br.com.VNLocacoes.VNLocacoes.entity.CarroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarroMapper {

    CarroMapper INSTANCE = Mappers.getMapper(CarroMapper.class);

    CarroEntity toEntity(CarroDTO carroDTO);

    CarroDTO toDTO(CarroEntity carro);
}
