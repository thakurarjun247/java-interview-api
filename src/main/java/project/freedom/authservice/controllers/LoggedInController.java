package project.freedom.authservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoggedInController {
    @GetMapping("/secured")
    public String secured(){
        return "you are logged in.";
    }


}
