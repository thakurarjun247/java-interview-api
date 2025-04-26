package project.freedom.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.service.UserService;

import java.util.Date;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/secured")
    public String health() {
        return "Hi Admin, you are loggedin on " + new Date();
    }



}
