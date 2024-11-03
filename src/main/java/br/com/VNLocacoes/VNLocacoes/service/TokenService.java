package br.com.VNLocacoes.VNLocacoes.service;

import br.com.VNLocacoes.VNLocacoes.entity.UsuarioEntity;
import br.com.VNLocacoes.VNLocacoes.exception.TokenVerificacaoExcecao;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String segredo;

    public String gerarToken(UsuarioEntity usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(segredo);

            String token = JWT.create()
                    .withIssuer("vn-locacoes-backend")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algoritmo);


            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Houve um erro durante a geração do Token");
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(segredo);

            return JWT.require(algoritmo)
                    .withIssuer("vn-locacoes-backend")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new TokenVerificacaoExcecao("Houve um erro na verificação do token");
        }
    }

    public Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(10L).toInstant(ZoneOffset.of("-03:00"));
    }
}
