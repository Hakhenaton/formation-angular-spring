package fr.sncf.comere.users.repository;

import java.util.UUID;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import fr.sncf.comere.users.models.User;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    
    private final JdbcTemplate jdbcTemplate;

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
            " (\"id\", \"first_name\", \"last_name\", \"email\", \"role\" \"date_of_birth\") " + 
            " VALUES (?, ?, ?, ?, ?, ?)";

        val updated = this.jdbcTemplate.update(
            insertQuery, 
            id,
            user.getFirstName(), 
            user.getLastName(), 
            user.getEmail(), 
            user.getRole(), 
            user.getDateOfBirth()
        );

        if (updated != 1){
            // cas d'erreur qu'on va probablement devoir gérer
        }

        user.setId(id);
    }
}
