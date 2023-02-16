package fr.sncf.comere.users.models;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserParameters {
    
    private final String firstName;

    private final String lastName;
   
    private final Date dateOfBirth;

    private final String email;

    private final UserRole role;
}
