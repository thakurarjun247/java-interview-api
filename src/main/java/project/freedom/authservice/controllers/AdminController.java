package project.freedom.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.service.UserService;

import java.util.Date;

@RestController("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/healthadmin")
    public String health() {
        return "Health is green as on  " + new Date();
    }

    @PostMapping("/signupadmin")
    //@RequestParam User user won't work — you should use @RequestBody for JSON payloads.
    public User signup(@RequestBody User user){
        return userService.save(user);
    }

}
