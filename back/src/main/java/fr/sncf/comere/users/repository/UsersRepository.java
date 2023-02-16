package fr.sncf.comere.users.repository;

import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.sncf.comere.users.models.User;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Repository
@RequiredArgsConstructor
public class UsersRepository {
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Faire persister un utilisateur en base de données
     * Cette méthode va aussi ajouter un identifiant unique via {@link User#setId(UUID)}
     * @param user
     */
    public void create(User user){

        val id = UUID.randomUUID();
        
        // Tout ça n'est pas très élégant car les paramètres sont passés de manière positionnelle.
        // En fait, quand on a beaucoup de paramètres, on veut éviter de les passer de cette manière.
        // En effet, il peut être pénible de devoir observer à quel index on se situe dans la liste de paramètres.
        // On préferera utiliser des clés nommées.
        val insertQuery = "INSERT INTO \"users\" " + 
            " (\"id\", \"first_name\", \"last_name\", \"email\", \"role\", \"date_of_birth\") " + 
            " VALUES (:id, :firstName, :lastName, :email, :role, :dateOfBirth)";

        val updated = this.jdbcTemplate.update(
            insertQuery, 
            Map.of(
                "id", id,
                "firstName", user.getFirstName(),
                "lastName", user.getLastName(),
                "email", user.getEmail(),
                "role", user.getRole().serialize(),
                "dateOfBirth", user.getDateOfBirth()
            )
        );

        if (updated != 1){
            throw new IllegalStateException(String.format("single insert query returned %d", updated));
        }

        user.setId(id);
    }
}
