package br.com.VNLocacoes.VNLocacoes.dto;

import br.com.VNLocacoes.VNLocacoes.entity.UsuarioPapel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {

    @NotNull(message = "O login do usuário é obrigatório")
    private String login;

    @NotNull(message = "A senha do usuário é obrigatória")
    private String senha;

    @NotNull(message = "O papel do usuário é obrigatório")
    private UsuarioPapel papel;
}
