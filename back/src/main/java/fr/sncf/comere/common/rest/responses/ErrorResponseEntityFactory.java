package fr.sncf.comere.common.rest.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Classe injectable permettant de créer facilement des {@link ResponseEntity}s qui encapsulent des {@link ErrorResponse} passées dans le corps de réponse. 
 */
@Component
public class ErrorResponseEntityFactory {
    
    /**
     * Créer une {@link ResponseEntity} comprenant le status HTTP passé en paramètre et dont le corps de réponse
     * est basé sur l'exception passée en paramètre.
     * @param ex L'exception qui a été levée qu'on cherche à représenter
     * @param status Le status HTTP à mettre en place dans la réponse
     * @return Une {@link ResponseEntity} qui encapsule une {@link ErrorResponse}
     */
    public ResponseEntity<ErrorResponse> createErrorResponseEntity(Throwable ex, HttpStatus status) {
        return ResponseEntity
            .status(status)
            .body(
                ErrorResponse.builder()
                    .message(ex.getMessage())
                    .type(ex.getClass().getSimpleName())
                    .build()
            );
    }
}
