package fr.sncf.comere.users.exceptions;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String email){
        super(String.format("%s already exists in the database", email));
    }
}
