package fr.sncf.comere.users.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.sncf.comere.common.repository.RepositoryHelpers;
import fr.sncf.comere.users.models.User;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class UsersRepository {
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final UserRowMapper userRowMapper;

    /**
     * Faire persister un utilisateur en base de données
     * Cette méthode va aussi ajouter un identifiant unique via {@link User#setId(UUID)}
     * @param user
     */
    public void create(User user){

        final var id = UUID.randomUUID();
        
        // Tout ça n'est pas très élégant car les paramètres sont passés de manière positionnelle.
        // En fait, quand on a beaucoup de paramètres, on veut éviter de les passer de cette manière.
        // En effet, il peut être pénible de devoir observer à quel index on se situe dans la liste de paramètres.
        // On préferera utiliser des clés nommées.
        final var insertQuery = "INSERT INTO \"users\" " + 
            " (\"id\", \"first_name\", \"last_name\", \"email\", \"role\", \"date_of_birth\") " + 
            " VALUES (:id, :firstName, :lastName, :email, :role, :dateOfBirth)";

        final var updated = this.jdbcTemplate.update(
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

    public Optional<User> findByEmail(String email){

        final var selectQuery = "SELECT * FROM \"users\" AS \"user\" WHERE \"user\".\"email\" = :email";

        return RepositoryHelpers.queryAsOptional(() -> this.jdbcTemplate.queryForObject(
            selectQuery,
            Collections.singletonMap("email", email),
            this.userRowMapper
        ));
    }

    public Optional<User> findById(UUID id){
        final var selectQuery = "SELECT * FROM \"users\" AS \"user\" WHERE \"user\".\"id\" = :id";

        return RepositoryHelpers.queryAsOptional(() -> this.jdbcTemplate.queryForObject(
            selectQuery,
            Collections.singletonMap("id", id.toString()),
            this.userRowMapper
        ));
    }

    public List<User> findAll(){

        final var selectQuery = "SELECT * FROM \"users\"";

        return this.jdbcTemplate.query(
            selectQuery,
            Collections.emptyMap(),
            this.userRowMapper
        );
    }

    public void delete(User user){

        final var deleteQuery = "DELETE FROM \"users\" AS \"user\" WHERE \"user\".\"id\" = :id";

        final var deleted = this.jdbcTemplate.update(deleteQuery, Collections.singletonMap("id", user.getId().toString()));

        if (deleted != 1){
            throw new IllegalStateException(String.format("no user or more than one user were deleted (%d)", deleted));
        }
    }
}
