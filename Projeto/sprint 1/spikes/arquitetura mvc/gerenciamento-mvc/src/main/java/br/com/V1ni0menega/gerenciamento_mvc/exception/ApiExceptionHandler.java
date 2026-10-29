package br.com.V1ni0menega.gerenciamento_mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleNaoEncontrado(ProdutoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpoErro(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleArgumentoInvalido(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoErro(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    private Map<String, Object> corpoErro(HttpStatus status, String mensagem) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status", status.value(),
                "erro", status.getReasonPhrase(),
                "mensagem", mensagem
        );
    }
}
