package fr.sncf.comere.common.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Des endpoints HTTP qui concernent l'authentification.
 */
@RestController
@RequestMapping(path = "/auth/state")
@RequiredArgsConstructor
public class AuthenticationController {
    
    /**
     * Récupérer le *nom* de l'utilisateur authentifié.
     * @return Une chaîne de caractères qui représente le nom de l'utilisateure authentifié.
     */
    @GetMapping
    public String getAuthenticationState(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
