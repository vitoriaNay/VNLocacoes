package br.com.VNLocacoes.VNLocacoes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private long id;

    @NotNull(message = "O nome do cliente é obrigatório")
    private String nome;

    @NotNull(message = "O CPF do cliente é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF precisa ter 11 dígitos")
    private String cpf;

    @NotNull(message = "O telefone do cliente é obrigatório")
    @Size(min = 10, max = 11, message = "Informe um telefone válido")
    private String telefone;

    @Pattern(regexp = "^([A-Za-z0-9_\\.]*)@([a-z]+)(\\.[a-z]+)$", message = "O E-mail informado é inválido")
    private String email;
}
