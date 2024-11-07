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
public class MarcaDTO {

    private long id;

    @NotNull(message = "O nome da marca é obrigatório")
    private String nome;
}
