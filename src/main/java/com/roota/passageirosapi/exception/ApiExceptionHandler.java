package com.roota.passageirosapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    //Tratar os erros
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> tratarErro(RuntimeException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    //Tratar as validações do dto
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(erro -> {erros.put(erro.getField(), erro.getDefaultMessage());
        });
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}
