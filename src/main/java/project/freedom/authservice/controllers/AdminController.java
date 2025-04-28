package project.freedom.authservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.exception.ExceptionType;
import project.freedom.authservice.exception.UserNotFoundException;
import project.freedom.authservice.repository.AdminRepository;
import project.freedom.authservice.service.AdminService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller")

public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/users")
    public List<User> findAll(){
        return adminService.findAll();
    }
    @GetMapping("/findbyemail")
    public User findByEmail(@RequestParam String email){
        Optional<User> optionalUser= adminService.findByEmail(email);
        return optionalUser.orElseThrow(()->new UserNotFoundException(ExceptionType.USER_NOT_FOUND));

    }

}
