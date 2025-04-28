package project.freedom.authservice.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SecurityRequirement(name = "cookieAuth")
public class LoggedInController {
    @GetMapping("/secured")
    public String secured(){
        return "you are logged in.";
    }


}
