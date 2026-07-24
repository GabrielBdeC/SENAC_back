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

    // Erros de validação (@Valid): devolve 400 com mapa campo → mensagem.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> tratar(MethodArgumentNotValidException e) {
        Map<String, String> erros = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(f ->
            erros.put(f.getField(), f.getDefaultMessage()));
        return erros;
    }

    // E-mail duplicado: 409 Conflict com a mensagem do service — não o texto cru do MySQL.
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> tratar(IllegalArgumentException e) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", e.getMessage());
        return erro;
    }
}

