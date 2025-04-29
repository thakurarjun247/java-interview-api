package project.freedom.authservice.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.exception.ExceptionType;
import project.freedom.authservice.exception.UserNotFoundException;
import project.freedom.authservice.service.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "Accessible only to Admin")
@SecurityRequirement(name = "cookieAuth")

public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/users")
    public List<User> findAll() {
        return adminService.findAll();
    }

    @GetMapping("/users/email/{email}")
    public User findByEmail(@PathVariable String email) {
        Optional<User> optionalUser = adminService.findByEmail(email);
        return optionalUser.orElseThrow(() -> new UserNotFoundException(ExceptionType.USER_NOT_FOUND));

    }

    @DeleteMapping("/users/email/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        adminService.deleteUserByEmail(email);
        return ResponseEntity.ok("User with email " + email + " deleted successfully.");
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAllUsers() {
        adminService.deleteAllUsers();
        return ResponseEntity.ok("All users deleted successfully.");
    }

}
