package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.dto.PagamentoDTO;
import br.com.VNLocacoes.VNLocacoes.entity.PagamentoEntity;
import br.com.VNLocacoes.VNLocacoes.mapper.PagamentoMapper;
import br.com.VNLocacoes.VNLocacoes.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    public PagamentoDTO salvarPagamento(PagamentoDTO pagamento) {
        PagamentoEntity pagamentoSalvo = pagamentoRepository.save(PagamentoMapper.INSTANCE.toEntity(pagamento));

        return PagamentoMapper.INSTANCE.toDTO(pagamentoSalvo);
    }
}
