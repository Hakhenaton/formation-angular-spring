package fr.sncf.comere.users.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

/**
 * Use case de lecture d'un ensemble d'utilisateurs
 */
@Service
@RequiredArgsConstructor
public class FindAllUsersUseCase {
    
    private final UsersRepository usersRepository;

    /**
     * Lire tous les utilisateurs de l'application
     * @return Une liste de {@link User}
     */
    public List<User> read(){
        return this.usersRepository.findAll();
    }
}
