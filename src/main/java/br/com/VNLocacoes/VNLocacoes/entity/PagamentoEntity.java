package br.com.VNLocacoes.VNLocacoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal valor;

    private LocalDate data = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private PagamentoTipo pagamentoTipo;

    @JsonIgnore
    @OneToOne(mappedBy = "pagamento")
    private AluguelEntity aluguel;

    public PagamentoEntity(BigDecimal valor) {
        this.valor = valor;
    }
}
