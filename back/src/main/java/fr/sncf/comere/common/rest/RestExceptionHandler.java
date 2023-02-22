package fr.sncf.comere.common.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestExceptionHandler {
    
    public ResponseEntity<ErrorResponse> createResponseEntity(Throwable ex, HttpStatus status) {
        return ResponseEntity
            .status(status)
            .body(
                ErrorResponse.builder()
                    .message(ex.getMessage())
                    .type(ex.getClass().getSimpleName())
                    .build()
            );
    }
}
