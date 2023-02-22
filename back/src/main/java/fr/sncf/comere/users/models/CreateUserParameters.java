package fr.sncf.comere.users.models;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class CreateUserParameters {
    
    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;
   
    @NonNull
    private final Date dateOfBirth;

    @NonNull
    private final String email;

    @NonNull
    private final UserRole role;
}
