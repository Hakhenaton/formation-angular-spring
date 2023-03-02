package fr.sncf.comere.users.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.sncf.comere.common.exceptions.ResourceNotFoundException;
import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

/** Use case de suppression d'utilisateur */
@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {
    
    private final UsersRepository usersRepository;

    /**
     * Supprimer un utilisateur via son identifiant.
     * @param id L'identifiant de l'utilisateur.
     */
    public void delete(UUID id){
        final var user = this.usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(User.class));

        this.usersRepository.delete(user);
    }
}
