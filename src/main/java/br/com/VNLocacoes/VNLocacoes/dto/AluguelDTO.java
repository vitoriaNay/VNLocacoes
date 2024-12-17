package br.com.VNLocacoes.VNLocacoes.dto;

import br.com.VNLocacoes.VNLocacoes.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDTO {
    private Long id;

    private LocalDate dataInicio;

    @NotNull(message = "Informe a data do fim do aluguel")
    private LocalDate dataFim;

    private StatusAluguel status = StatusAluguel.ATIVO;

    private PagamentoEntity pagamento;

    @NotNull(message = "Informe os dados do cliente")
    private ClienteEntity cliente;

    @NotNull(message = "Informe os dados do veículo")
    private CarroEntity carro;
}
