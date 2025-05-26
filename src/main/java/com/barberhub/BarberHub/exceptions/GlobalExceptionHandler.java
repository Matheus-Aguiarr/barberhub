package com.barberhub.BarberHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//Indica que a classe vai tratar exceptions de todos os controllers
@ControllerAdvice
public class GlobalExceptionHandler {

//    Quando acontecer essa exception especifica, o metodo abaixo deve ser executado
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //O metodo retorna um ResponseEntity<Map<String, String>> -> Um mapa onde a chave é o nome do campo com erro e o valor é a mensagem de erro. (Map<Key, Value>)
    public ResponseEntity<Map<String, String>> handleValidationsExceptions(MethodArgumentNotValidException ex) {
//        Cria um mapa vazio que vai armazenar os erros no formato -> Chave: Valor; -> Exemplo: "email": "Email invalido".
        Map<String, String> errors = new HashMap<>();

//        -> ex.getBindingResult().getFieldErrors() -> Pega todos os erros que ocorreram nos campos validados.
        ex.getBindingResult().getFieldErrors()
//                Para cada error: error.getField() -> Pega o nome do campo que gerou o erro (ex.:"email") -> error.getDefaultMessage() -> Mensagem assosicada ao erro (a que foi colocada no message = "" do DTO)
                .forEach(error -> errors
//                        O forEach vai percorrer todos os erros e adicionar ao mapa de errors, criado la em cima, usando o metodo -> (.put())
                        .put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
