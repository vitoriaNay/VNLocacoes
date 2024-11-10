package br.com.VNLocacoes.VNLocacoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ManipuladorGlobalExcecao {

    @ExceptionHandler(RegistroNaoEncontradoExcecao.class)
    public ResponseEntity<RespostaExcecao> registroNaoEncontradoExcecao(RegistroNaoEncontradoExcecao e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RespostaExcecao(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(RegistroJaExisteExcecao.class)
    public ResponseEntity<RespostaExcecao> registroJaExisteExcecao(RegistroJaExisteExcecao e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new RespostaExcecao(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()));

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespostaExcecao> corpoRequisicaoVazioExcecao(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RespostaExcecao(HttpStatus.BAD_REQUEST.value(),
                        "O corpo da requisição precisa ser enviado"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaExcecao> validacaoRequisicaoExcecao(MethodArgumentNotValidException e) {
        List<String> listaErros = new ArrayList<String>();

        e.getAllErrors().forEach((erro) -> listaErros.add(erro.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RespostaExcecao(HttpStatus.BAD_REQUEST.value(),
                        "Dados inválidos no corpo da requisição",
                        listaErros));
    }

    @ExceptionHandler(TokenVerificacaoExcecao.class)
    public ResponseEntity<RespostaExcecao> tokenVerificacaoExcecao(TokenVerificacaoExcecao e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RespostaExcecao(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
//    @ExceptionHandler(StackOverflowError.class)
//    public ResponseEntity<RespostaExcecao> erro(StackOverflowError e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new RespostaExcecao(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
//    }
}
