package project.freedom.authservice.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Get the Authentication object, which represents the current user.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // Create a SecurityContextLogoutHandler and perform the logout.
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "You are logged out";
    }

   @Override
   public Optional<String> getCurrentUser() {
        // Get the Authentication object from the SecurityContextHolder.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Return the username of the logged-in user.
            return Optional.of(authentication.getName());
        }
        return Optional.empty(); // Or return "anonymousUser", or handle as appropriate for your application
    }
}
