package fr.sncf.comere.common.rest.responses;

import lombok.Builder;
import lombok.Getter;

/**
 * Objet représentant un corps de réponse symbolisant une erreur sur la couche REST (HTTP).
 */
@Builder
@Getter
public class ErrorResponse {
    
    /**
     * Un message technique lié à l'erreur.
     * Cette propriété est uniquement fournie à titre d'information technique (pour le debug).
     */
    private final String message;

    /**
     * Le type de l'erreur. Typiquement ici on trouvera le nom de la classe d'exception Java qui a été levée lors de l'erreur.
     * On pourra se baser sur ce discriminant pour décider du message à afficher côté front lorsqu'on gère des cas d'erreur.
     */
    private final String type;
}
