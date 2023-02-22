package fr.sncf.comere.users.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.sncf.comere.common.exceptions.ResourceNotFoundException;
import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {
    
    private final UsersRepository usersRepository;

    public void delete(UUID id){
        final var user = this.usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(User.class));

        this.usersRepository.delete(user);
    }
}
