package fr.sncf.comere.users.rest.responses;

import org.springframework.stereotype.Component;

import fr.sncf.comere.users.models.User;

/**
 * Classe injectable permettant de mapper un {@link User} vers un objet {@link UserResponse}.
 */
@Component
public class UserResponseMapper {

    public UserResponse map(User user){
        return UserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .lastName(user.getLastName())
            .firstName(user.getFirstName())
            .role(user.getRole())
            .dateOfBirth(user.getDateOfBirth())
            .build();
    }
    
}
