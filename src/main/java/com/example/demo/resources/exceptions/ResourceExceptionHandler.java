package com.example.demo.resources.exceptions;

import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //indica que essa classe é responsável por tratar possíveis erros nas requisições
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) //padrão do frame pra funcionar
    public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandarError err = new StandarError(
                System.currentTimeMillis(),
                status.value(),
                "Não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}