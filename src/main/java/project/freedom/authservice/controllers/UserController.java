package project.freedom.authservice.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.freedom.authservice.service.UserService;

@RestController
@SecurityRequirement(name = "cookieAuth")
@RequestMapping("/user") // common path prefix for user-specific APIs
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/loggedin")
    public String isLoggedIn(){
        return "If you see this, you are loggedin.";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
     return userService.logout(request,response);
    }

}
