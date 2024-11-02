package br.com.VNLocacoes.VNLocacoes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespostaExcecao {

    private int status;
    private String message;
    private List<String> erros;

    public RespostaExcecao(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RespostaExcecao(int status, String message, List<String> erros) {
        this.status = status;
        this.message = message;
        this.erros = erros;
    }
}
