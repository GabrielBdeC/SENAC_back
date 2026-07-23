package br.com.nonna.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice: um lugar central que trata erros de todos os controllers.
// Substitui os blocos try/catch que seriam repetidos em cada método.
@RestControllerAdvice
public class ValidacaoHandler {

    // Captura a exceção que o @Valid lança quando a validação falha.
    // @ResponseStatus(BAD_REQUEST) devolve 400 automaticamente.
    // O mapa campo → mensagem é o que o front recebe para mostrar em cada input:
    // { "nome": "O nome é obrigatório", "preco": "O preço deve ser maior que zero" }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> tratar(MethodArgumentNotValidException e) {
        Map<String, String> erros = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(f ->
            erros.put(f.getField(), f.getDefaultMessage()));
        return erros;
    }
}
