package java.interview.authservice.service;

import java.interview.authservice.entity.User;

import java.util.List;
import java.util.Optional;

//On the interface (HomeService) → ❌ No annotation needed

//On the implementation class (HomeServiceImpl) → ✅ Use @Service
public interface AdminService {
    //Return User directly instead of ResponseEntity<User>:
    //ResponseEntity is a web layer concern, and it's better to keep service layer pure and free from HTTP-specific logic.
    //Let  Controller handle the wrapping with ResponseEntity.
    public List<User> findAll();

    public Optional<User> findByEmail(String email);

    void deleteUserByEmail(String email);

    void deleteAllUsers();
}
