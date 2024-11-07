package br.com.VNLocacoes.VNLocacoes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private long id;

    @NotNull(message = "O nome da categoria é obrigatório")
    private String nome;

    private String descricao;
}
