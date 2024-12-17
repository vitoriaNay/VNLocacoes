package br.com.VNLocacoes.VNLocacoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "aluguel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private StatusAluguel status;

    @JsonIgnore
    @OneToOne // Um Aluguel estará associado a no mínimo 1 e no máximo 1 ocorrência da tabela pagamento
    @JoinColumn(name = "id_pagamento", nullable = false)
    private PagamentoEntity pagamento;

    @ManyToOne // Um Aluguel estará associado a no mínimo 1 e no máximo n ocorrências da tabela cliente
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne // Um Aluguel estará associado a no mínimo 1 e no máximo n ocorrências da tabela carro
    @JoinColumn(name = "id_carro", nullable = false)
    private CarroEntity carro;
}
