package fr.sncf.comere.users.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.sncf.comere.common.rest.responses.ErrorResponse;
import fr.sncf.comere.common.rest.responses.ErrorResponseEntityFactory;
import fr.sncf.comere.users.exceptions.EmailExistsException;
import fr.sncf.comere.users.exceptions.InvalidDateOfBirthException;
import lombok.RequiredArgsConstructor;

/**
 * {@link RestControllerAdvice} permettant de faire la translation entre les exceptions du domaine métier `users`
 * et les réponses HTTP associées.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class UsersControllerAdvice {

    private final ErrorResponseEntityFactory handler;
    
    @ExceptionHandler({ InvalidDateOfBirthException.class })
    public ResponseEntity<ErrorResponse> handleInvalidDateOfBirth(InvalidDateOfBirthException ex){
        return this.handler.createErrorResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EmailExistsException.class })
    public ResponseEntity<ErrorResponse> handleExistingEmail(EmailExistsException ex){
        return this.handler.createErrorResponseEntity(ex, HttpStatus.CONFLICT);
    }
    
}
