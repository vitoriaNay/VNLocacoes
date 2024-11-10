package br.com.VNLocacoes.VNLocacoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // ESPECIFICA QUE ESTA CLASSE É PERSISTENTE (MAPEIA UMA ENTIDADE DO BANCO DE DADOS)
@Table(name = "cliente") // ESPECIFICA QUAL TABELA DO BANCO DE DADOS ESTA CLASSE VAI SER PERSISTIDA

// ANOTAÇÕES DO LOMBOK PARA REDUZIR O CÓDIGO BOILERPLATE
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {

    @Id // DEFINE ESTE CAMPO COMO SENDO A (OU PARTE DA) CHAVE PRIMÁRIA DA ENTIDADE
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DEFINE QUE ESTE CAMPO VAI SER GERADO POR UM GERADOR (AUTOMÁTICO)
    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    List<AluguelEntity> listaAlugueis = new ArrayList<>();

    // Contrututor somente com a argumento do id que será usado no AluguelService

    public ClienteEntity(Long id) {
        this.id = id;
    }
}
