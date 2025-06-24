package java.interview.authservice.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.interview.authservice.service.AuthService;

import java.util.Optional;

@RestController
@SecurityRequirement(name = "cookieAuth")
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Accessible to any logged in users, irrespective of role")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return authService.logout(request, response);
    }

    @GetMapping("/me")
    public Optional<String> getCurrentUser() {
        return authService.getCurrentUser();
    }


}
