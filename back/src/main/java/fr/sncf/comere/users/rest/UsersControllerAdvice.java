package fr.sncf.comere.users.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.sncf.comere.common.rest.ErrorResponse;
import fr.sncf.comere.common.rest.RestExceptionHandler;
import fr.sncf.comere.users.exceptions.EmailExistsException;
import fr.sncf.comere.users.exceptions.InvalidDateOfBirthException;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class UsersControllerAdvice {

    private final RestExceptionHandler handler;
    
    @ExceptionHandler({ InvalidDateOfBirthException.class })
    public ResponseEntity<ErrorResponse> handleInvalidDateOfBirth(InvalidDateOfBirthException ex){
        return this.handler.createResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EmailExistsException.class })
    public ResponseEntity<ErrorResponse> handleExistingEmail(EmailExistsException ex){
        return this.handler.createResponseEntity(ex, HttpStatus.CONFLICT);
    }
    
}
