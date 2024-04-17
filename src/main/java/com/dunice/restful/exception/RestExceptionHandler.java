package com.dunice.restful.exception;

import com.dunice.restful.service.ClientServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> HandleException(MethodArgumentNotValidException ex ) {
        return ResponseEntity
                .badRequest()
                .body(ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> HandleException(EntityNotFoundException ex){
       return ResponseEntity.status(NOT_FOUND).body(ex.getMessage());
    }

}
