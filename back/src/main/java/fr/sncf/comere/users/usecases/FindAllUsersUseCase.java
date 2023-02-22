package fr.sncf.comere.users.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllUsersUseCase {
    
    private final UsersRepository usersRepository;

    public List<User> read(){
        return this.usersRepository.findAll();
    }
}
