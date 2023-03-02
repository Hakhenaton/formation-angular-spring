package fr.sncf.comere.users.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = -6195955904636828447L;

    @Setter
    private UUID id;
    
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

    @NonNull
    private final String password;

    public UUID getId(){
        return this.id;
    }
}
