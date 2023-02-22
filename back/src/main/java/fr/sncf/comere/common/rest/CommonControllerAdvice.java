package fr.sncf.comere.common.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.sncf.comere.common.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class CommonControllerAdvice {
    
    private final RestExceptionHandler handler;

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){
        return this.handler.createResponseEntity(ex, HttpStatus.NOT_FOUND);
    }
}
