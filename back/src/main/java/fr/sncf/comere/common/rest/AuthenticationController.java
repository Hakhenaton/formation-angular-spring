package fr.sncf.comere.common.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sncf.comere.common.configuration.CustomUserDetailsService.CustomUserDetails;
import fr.sncf.comere.users.rest.responses.UserResponse;
import fr.sncf.comere.users.rest.responses.UserResponseMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Des endpoints HTTP qui concernent l'authentification.
 */
@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserResponseMapper userResponseMapper;
    
    /**
     * Récupérer le *nom* de l'utilisateur authentifié.
     * @return Une chaîne de caractères qui représente le nom de l'utilisateure authentifié.
     */
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<UserResponse> auth(){
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            return ResponseEntity.noContent().build();
        }
        final var userDetails = (CustomUserDetails)authentication.getPrincipal();
        return ResponseEntity.ok(this.userResponseMapper.map(userDetails.getUser()));
    }
}
