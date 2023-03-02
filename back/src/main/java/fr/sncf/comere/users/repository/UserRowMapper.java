package fr.sncf.comere.users.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.models.UserRole;

/**
 * Un {@link RowMapper} qui permet de mapper les résultats d'une requête SQL de lecture vers
 * des objets {@link User} de notre domaine métier.
 */
@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    @Nullable
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
            .id(UUID.fromString(rs.getString("id")))
            .lastName(rs.getString("last_name"))
            .firstName(rs.getString("first_name"))
            .dateOfBirth(rs.getTimestamp("date_of_birth"))
            .email(rs.getString("email"))
            .role(UserRole.deserialize(rs.getString("role")))
            .password(rs.getString("password"))
            .build();
    }
    
}
