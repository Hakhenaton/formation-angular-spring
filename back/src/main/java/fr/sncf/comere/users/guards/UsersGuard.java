package fr.sncf.comere.users.guards;

import org.springframework.stereotype.Component;

import fr.sncf.comere.common.configuration.CustomUserDetailsService.CustomUserDetails;
import fr.sncf.comere.users.models.User;
import fr.sncf.comere.users.models.UserRole;

@Component("usersGuard")
public class UsersGuard {
    
    public boolean canCreate(User userToCreate, Object principal){

        if (principal instanceof CustomUserDetails){

            final var caller = ((CustomUserDetails)principal).getUser();

            if (caller.getRole().equals(UserRole.ADMINISTRATOR)){
                return true;
            }
        }

        return userToCreate.getRole().equals(UserRole.USER);
    }
}
