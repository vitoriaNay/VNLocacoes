package br.com.VNLocacoes.VNLocacoes.dto;

import br.com.VNLocacoes.VNLocacoes.entity.CategoriaEntity;
import br.com.VNLocacoes.VNLocacoes.entity.MarcaEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarroDTO {

    private long id;

    @NotNull(message = "O modelo do veículo é obrigatório")
    private String modelo;

    @NotNull(message = "O ano de fabricação do veículo é obrigatório")
    private int anoFabricacao;

    @NotNull(message = "A placa do veículo é obrigatório")
    private String placa;

    @NotNull(message = "Informe a disponibilidade do veículo")
    private boolean disponibilidade = true;

    @NotNull(message = "Informe o valor do aluguel diário do veículo")
    private float valorDiaria;

    @NotNull(message = "Informe a marca do carro")
    private MarcaEntity marca;

    @NotNull(message = "Informe a categoria do veículo")
    private CategoriaEntity categoria;
}
