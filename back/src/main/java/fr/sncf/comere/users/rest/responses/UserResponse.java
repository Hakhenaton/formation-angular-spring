package fr.sncf.comere.users.rest.responses;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.sncf.comere.users.models.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    
    private final UUID id;
    
    private final String firstName;

    private final String lastName;
   
    private final Date dateOfBirth;

    private final String email;

    @Getter(AccessLevel.NONE)
    private final UserRole role;

    public String role(){
        return this.role.serialize();
    }
}
