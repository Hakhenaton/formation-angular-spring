package fr.sncf.comere.users.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.sncf.comere.common.exceptions.ResourceNotFoundException;
import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

/** Use case de lecture d'un utilisateur unique */
@Service
@RequiredArgsConstructor
public class ReadUserUseCase {
    
    private final UsersRepository usersRepository;

    /**
     * Lire un utilisateur via son identifiant.
     * @param id l'identifiant de l'utilisateur
     * @return L'utilisateur qui vient d'être lu
     */
    public User read(UUID id){
        final var user = this.usersRepository.findById(id);

        return user.orElseThrow(() -> new ResourceNotFoundException(User.class));
    }
}
