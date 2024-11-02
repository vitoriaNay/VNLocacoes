package br.com.VNLocacoes.VNLocacoes.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroNaoEncontradoExcecao extends RuntimeException {
    public RegistroNaoEncontradoExcecao(String message) {
        super(message);
    }
}
