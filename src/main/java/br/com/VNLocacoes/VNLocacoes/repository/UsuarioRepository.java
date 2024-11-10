package br.com.VNLocacoes.VNLocacoes.repository;

import br.com.VNLocacoes.VNLocacoes.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    UserDetails findByLogin(String login);
}
