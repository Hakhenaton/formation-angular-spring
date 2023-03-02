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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


/**
 * L'ensemble des endpoints HTTP concernant la partie utilisateurs.
 */
@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UsersRestController {
    
    private final ReadUserUseCase readUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserResponseMapper userResponseMapper;

    /**
     * Endpoint permettant d'ajouter un utilisateur à la collection d'utilisateurs.
     * @param request Les données à passer dans le corps de requête nécessaires à la création d'un utilisateur.
     * @return Une {@link UserReponse} représentant l'utilisateur venant d'être créé.
     * @throws fr.sncf.comere.users.exceptions.EmailExistsException Si l'email passé en paramètre existe déjà
     * @throws fr.sncf.comere.users.exceptions.InvalidDateOfBirthException Si le date de naissance est incorrecte
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        final var user = this.createUserUseCase.create(
            CreateUserParameters.builder()
                .email(request.getEmail())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .role(request.getRole())
                .dateOfBirth(request.getDateOfBirth())
                .password(request.getPassword())
                .build()
        );       
        return this.userResponseMapper.map(user);
    }

    /**
     * Endpoint permettant de lire un utilisateur via son identifiant unique.
     * @param id L'identifiant de l'utilisateur à lire
     * @return Une {@link UserReponse} représentant l'utilisateur s'il a été trouvé.
     * @throws fr.sncf.comere.common.exceptions.ResourceNotFoundException si l'utilisateur n'a pas été trouvé.
     */
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") UUID id){

        return this.userResponseMapper.map(this.readUserUseCase.read(id));
    }

    /**
     * Endpoint permettant de lire l'ensemble des utilisateurs dans la collection.
     * @return Une liste de {@link UserResponse} correspondant à la l'ensemble des utilisateurs.
     */
    @GetMapping
    public List<UserResponse> findAll(){

        return this.findAllUsersUseCase.read()
            .stream()
            .map(this.userResponseMapper::map)
            .toList();
    }

    /**
     * Endpoint permettant de supprimer un utilisateur.
     * @param id L'identifiant de l'utilisateur à supprimer.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") UUID id){
        this.deleteUserUseCase.delete(id);
    }
}
