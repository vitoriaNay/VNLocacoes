package br.com.VNLocacoes.VNLocacoes.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroNaoEncontradoExcecao extends RuntimeException {

    public RegistroNaoEncontradoExcecao() {super("Registro não encontrado na base de dados"); }

    public RegistroNaoEncontradoExcecao(String message) {
        super(message);
    }
}
