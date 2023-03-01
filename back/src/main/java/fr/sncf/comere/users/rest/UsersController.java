package fr.sncf.comere.users.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.sncf.comere.users.models.CreateUserParameters;
import fr.sncf.comere.users.rest.requests.CreateUserRequest;
import fr.sncf.comere.users.rest.responses.UserResponse;
import fr.sncf.comere.users.rest.responses.UserResponseMapper;
import fr.sncf.comere.users.usecases.CreateUserUseCase;
import fr.sncf.comere.users.usecases.DeleteUserUseCase;
import fr.sncf.comere.users.usecases.FindAllUsersUseCase;
import fr.sncf.comere.users.usecases.ReadUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UsersController {
    
    private final ReadUserUseCase readUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserResponseMapper userResponseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserRequest request){
        final var user = this.createUserUseCase.create(
            CreateUserParameters.builder()
                .email(request.getEmail())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .role(request.getRole())
                .dateOfBirth(request.getDateOfBirth())
                .build()
        );       
        return this.userResponseMapper.map(user);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") UUID id, HttpServletRequest request){

        final var session = request.getSession();
        System.out.println(session.getAttribute("bonjour"));

        return this.userResponseMapper.map(this.readUserUseCase.read(id));
    }

    @GetMapping
    public List<UserResponse> findAll(HttpServletRequest request){

        final var session = request.getSession();
        session.setAttribute("bonjour", "au revoir");

        return this.findAllUsersUseCase.read()
            .stream()
            .map(this.userResponseMapper::map)
            .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") UUID id){
        this.deleteUserUseCase.delete(id);
    }

}
