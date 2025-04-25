package project.freedom.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.service.UserService;

import java.util.Date;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String hello() {
        return "hello " + new Date();
    }

    @PostMapping("/signup")
    //@RequestParam User user won't work — you should use @RequestBody for JSON payloads.
    public User signup(@RequestBody User user){
        return userService.save(user);
    }


    @GetMapping("/find-by-email")
    public User findByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
