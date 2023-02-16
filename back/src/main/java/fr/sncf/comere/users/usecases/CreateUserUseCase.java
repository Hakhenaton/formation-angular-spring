package fr.sncf.comere.users.usecases;

import java.util.Date;

import org.springframework.stereotype.Service;

import fr.sncf.comere.users.exceptions.InvalidDateOfBirthException;
import fr.sncf.comere.users.models.CreateUserParameters;
import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    
    private final UsersRepository usersRepository;

    public User create(CreateUserParameters parameters){

        final var now = new Date();
        if (parameters.getDateOfBirth().after(now)){
            throw new InvalidDateOfBirthException(parameters.getDateOfBirth());
        }

        val user = User.builder()
            .email(parameters.getEmail())
            .firstName(parameters.getFirstName())
            .lastName(parameters.getLastName())
            .dateOfBirth(parameters.getDateOfBirth())
            .role(parameters.getRole())
            .build();

        this.usersRepository.create(user);

        return user;
    }
}
