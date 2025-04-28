package project.freedom.authservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.repository.HomeRepository;
import project.freedom.authservice.service.HomeService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import project.freedom.authservice.entity.LoginRequest;

import java.util.Date;

@Tag(name = "Home Controller", description = "No login needed")

@RestController
public class HomeController {
    @Autowired
    private HomeService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/health")
    public String health() {
        return "Health is green as on  " + new Date();
    }

    @PostMapping("/signup")
    //@RequestParam User user won't work — you should use @RequestBody for JSON payloads.
    public User signup(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            return "Login successful!";
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }
    }
    @GetMapping("/invalid-session")
    public String invalidSession(){
        return "invalid session";
    }




}
