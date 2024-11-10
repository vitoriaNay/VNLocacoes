package br.com.VNLocacoes.VNLocacoes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoDTO {

    @NotNull(message = "O login do usuário é obrigatório")
    private String login;
    @NotNull(message = "A senha do usuário é obrigatória")
    private String senha;
}
