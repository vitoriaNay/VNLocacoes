package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.dto.AutenticacaoDTO;
import br.com.VNLocacoes.VNLocacoes.dto.LoginRespostaDTO;
import br.com.VNLocacoes.VNLocacoes.dto.RegistroDTO;
import br.com.VNLocacoes.VNLocacoes.entity.UsuarioEntity;
import br.com.VNLocacoes.VNLocacoes.repository.UsuarioRepository;
import br.com.VNLocacoes.VNLocacoes.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginRespostaDTO> login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        UsernamePasswordAuthenticationToken loginSenha
                = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(),
                autenticacaoDTO.getSenha());
        Authentication auth = authenticationManager.authenticate(loginSenha);

        String token = tokenService.gerarToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginRespostaDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegistroDTO registroDTO) {
        if (usuarioRepository.findByLogin(registroDTO.getLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String hashSenha = new BCryptPasswordEncoder().encode(registroDTO.getSenha());

        UsuarioEntity novoUsuario = new UsuarioEntity(registroDTO.getLogin(), hashSenha, registroDTO.getPapel());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioRepository.save(novoUsuario));
    }
}
