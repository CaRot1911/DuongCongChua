package com.vti.finalexam.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFound(EntityNotFoundException exception){
        return ResponseEntity.status(400).body("Entity not found exception");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFound(UsernameNotFoundException exception){
        return ResponseEntity.status(400).body("Account dose not exits");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerEntityNotFound(Exception exception){
        return ResponseEntity.status(500).body("Back end error");
    }
}
