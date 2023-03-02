package fr.sncf.comere.users.usecases;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.sncf.comere.users.exceptions.EmailExistsException;
import fr.sncf.comere.users.exceptions.InvalidDateOfBirthException;
import fr.sncf.comere.users.models.CreateUserParameters;
import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

/**
 * Use case métier de création d'utilisateur.
 */
@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Créer un utilisateur.
     * @param parameters Les données associées à l'utilisateur qu'on veut créer.
     * @return L'utilisateur nouvellement créé.
     */
    public User create(CreateUserParameters parameters){

        final var now = new Date();
        if (parameters.getDateOfBirth().after(now)){
            throw new InvalidDateOfBirthException(parameters.getDateOfBirth());
        }

        if (this.usersRepository.findByEmail(parameters.getEmail()).isPresent()){
            throw new EmailExistsException(parameters.getEmail());
        }

        final var user = User.builder()
            .email(parameters.getEmail())
            .firstName(parameters.getFirstName())
            .lastName(parameters.getLastName())
            .dateOfBirth(parameters.getDateOfBirth())
            .role(parameters.getRole())
            .password(this.passwordEncoder.encode(parameters.getPassword()))
            .build();

        this.usersRepository.create(user);

        return user;
    }
}
