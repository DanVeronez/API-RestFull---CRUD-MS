package com.teste.primeiroexemplo.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.primeiroexemplo.model.error.ErrorMessage;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> HandlerResourceNotFoundException(ResourceNotFoundException ex){
        
    ErrorMessage errorMessage = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
    
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

    }
}
