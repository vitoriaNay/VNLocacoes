package br.com.VNLocacoes.VNLocacoes.dto;

import br.com.VNLocacoes.VNLocacoes.entity.CarroEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private long id;

    @NotNull(message = "O nome da categoria é obrigatório")
    private String nome;

    private String descricao;

    @JsonIgnore
    private List<CarroEntity> listaCarros = new ArrayList<>();
}
