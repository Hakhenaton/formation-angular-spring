package fr.sncf.comere.users.rest.requests;

import java.util.Date;

import fr.sncf.comere.users.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
   
    @NotNull
    private Date dateOfBirth;

    @Email
    private String email;

    @NotNull
    private String role;
}
