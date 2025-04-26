package project.freedom.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.exception.ExceptionType;
import project.freedom.authservice.exception.UserNotFoundException;
import project.freedom.authservice.repository.UserRepository;
import project.freedom.authservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import project.freedom.authservice.entity.LoginRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
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

    @GetMapping("/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @GetMapping("/findbyemail")
    public User findByEmail(@RequestParam String email){
        Optional<User> optionalUser= userService.findByEmail(email);
        if (optionalUser.isPresent())
            return optionalUser.get();
        throw  new UserNotFoundException(ExceptionType.USER_NOT_FOUND);
    }


}
