package fr.sncf.comere.users.models;

import java.util.Date;
import java.util.UUID;
import java.util.Optional;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
public class User {

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

    public Optional<UUID> getId(){
        return Optional.ofNullable(this.id);
    }
}
