package br.com.VNLocacoes.VNLocacoes.mapper;

import br.com.VNLocacoes.VNLocacoes.dto.PagamentoDTO;
import br.com.VNLocacoes.VNLocacoes.entity.PagamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    PagamentoEntity toEntity(PagamentoDTO pagamentoDTO);

    PagamentoDTO toDTO(PagamentoEntity pagamento);
}
