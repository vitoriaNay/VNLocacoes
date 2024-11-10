package br.com.VNLocacoes.VNLocacoes.repository;

import br.com.VNLocacoes.VNLocacoes.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
    Optional<CarroEntity> findByPlaca(String placa);

    @Query(value = "SELECT * FROM carro WHERE disponibilidade = :disponibilidade", nativeQuery = true)
    List<CarroEntity> findByDisponibilidade(boolean disponibilidade);
}
