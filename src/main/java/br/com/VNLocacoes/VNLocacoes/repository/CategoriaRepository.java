package br.com.VNLocacoes.VNLocacoes.repository;

import br.com.VNLocacoes.VNLocacoes.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

}
