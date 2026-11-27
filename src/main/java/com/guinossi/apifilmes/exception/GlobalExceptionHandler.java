package com.guinossi.apifilmes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail erroValidacao(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail;
        problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Erro na validação dos dados!");
        problemDetail.setTitle("Erro");

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().stream()
                .forEach(erro -> {
                    erros.put(erro.getField(), erro.getDefaultMessage());
                });

        problemDetail.setProperty("erros", erros);
        problemDetail.setProperty("timestamp", LocalDate.now());

        return problemDetail;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail erroRequisicao(ResponseStatusException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), ex.getReason());

        problemDetail.setTitle("Erro na requisição");
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }
}
