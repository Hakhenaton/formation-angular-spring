package fr.sncf.comere.users.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.sncf.comere.users.models.CreateUserParameters;
import fr.sncf.comere.users.models.UserRole;
import fr.sncf.comere.users.rest.requests.CreateUserRequest;
import fr.sncf.comere.users.rest.responses.UserResponse;
import fr.sncf.comere.users.rest.responses.UserResponseMapper;
import fr.sncf.comere.users.usecases.CreateUserUseCase;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UsersController {
    
    private final CreateUserUseCase createUserUseCase;
    private final UserResponseMapper userResponseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserRequest request){
        val user = this.createUserUseCase.create(
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
}
