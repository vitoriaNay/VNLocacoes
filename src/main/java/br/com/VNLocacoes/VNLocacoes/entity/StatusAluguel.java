package br.com.VNLocacoes.VNLocacoes.entity;

import lombok.Getter;

@Getter
public enum StatusAluguel {

    ATIVO("ativo"),
    INATIVO("inativo");

    private final String status;

    StatusAluguel(String status) {
        this.status = status;
    }
}
