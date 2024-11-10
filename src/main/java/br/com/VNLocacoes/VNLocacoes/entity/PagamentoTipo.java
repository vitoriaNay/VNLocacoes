package br.com.VNLocacoes.VNLocacoes.entity;

import lombok.Getter;

@Getter
public enum PagamentoTipo {

    CARTAO_DEBITO("Cartão de débito"),
    CARTAO_CREDITO("Cartão de crédito"),
    PIX("pix"),
    DINHEIRO("dinheiro");

    private final String tipo;

    PagamentoTipo(String tipo) {
        this.tipo = tipo;
    }

}
