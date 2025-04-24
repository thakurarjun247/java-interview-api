package project.freedom.authservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String hello(){
        return "hello "+new Date();
    }

}
