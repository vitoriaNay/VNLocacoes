package br.com.VNLocacoes.VNLocacoes.dto;

import br.com.VNLocacoes.VNLocacoes.entity.AluguelEntity;
import br.com.VNLocacoes.VNLocacoes.entity.PagamentoTipo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {
    private Long id;

    @NotNull(message = "Informe o valor do pagamento")
    private BigDecimal valor;

    private LocalDate data = LocalDate.now();

    @NotNull(message = "Informe o tipo do pagamento")
    private PagamentoTipo pagamentoTipo;

    private AluguelEntity aluguel;
}
