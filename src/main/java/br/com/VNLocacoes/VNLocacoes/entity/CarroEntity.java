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

    @Column(unique = true)
    private String placa;

    private boolean disponibilidade;

    private BigDecimal valorDiaria;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    MarcaEntity marca;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Carro_Categoria",
            joinColumns = @JoinColumn(name = "id_carro", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_categoria", nullable = false)
    )
    private List<CategoriaEntity> listaCategorias = new ArrayList<>();
}
