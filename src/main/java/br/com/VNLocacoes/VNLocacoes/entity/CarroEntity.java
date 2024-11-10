package br.com.VNLocacoes.VNLocacoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String modelo;

    private int anoFabricacao;

    private String placa;

    private boolean disponibilidade;

    private BigDecimal valorDiaria;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private MarcaEntity marca;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    @JsonIgnore
    @OneToMany(mappedBy = "carro")
    private List<AluguelEntity> listaAlugueis = new ArrayList<>();

    // Contrututor somente com a argumento da placa que será usado no AluguelService
    public CarroEntity(String placa) {
        this.placa = placa;
    }
}
