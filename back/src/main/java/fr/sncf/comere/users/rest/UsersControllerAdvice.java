package fr.sncf.comere.users.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.sncf.comere.users.exceptions.InvalidDateOfBirthException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class UsersControllerAdvice {
    
    @ExceptionHandler({ InvalidDateOfBirthException.class })
    public String handleInvalidDateOfBirth(HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return "date invalide !";
    }
    
}
