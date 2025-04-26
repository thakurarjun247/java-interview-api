package project.freedom.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.service.UserService;

import java.util.Date;

@RestController
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/health")
    public String health() {
        return "Health is green as on  " + new Date();
    }

    @PostMapping("/signup")
    //@RequestParam User user won't work — you should use @RequestBody for JSON payloads.
    public User signup(@RequestBody User user){
        return userService.save(user);
    }




}
