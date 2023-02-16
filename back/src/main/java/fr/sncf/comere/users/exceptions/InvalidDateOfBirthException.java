package fr.sncf.comere.users.exceptions;

import java.util.Date;

public class InvalidDateOfBirthException extends RuntimeException {
    
    public InvalidDateOfBirthException(Date invalidDate){
        super(String.format("invalid date of birth %s was supplied", invalidDate.toString()));
    }
}