package project.freedom.authservice.service;

import project.freedom.authservice.entity.User;

import java.util.Optional;

//On the interface (HomeService) → ❌ No annotation needed

//On the implementation class (HomeServiceImpl) → ✅ Use @Service
public interface HomeService {
    //Return User directly instead of ResponseEntity<User>:
    //ResponseEntity is a web layer concern, and it's better to keep service layer pure and free from HTTP-specific logic.
    //Let  Controller handle the wrapping with ResponseEntity.
    public User save(User user);

    public Optional<User> findByEmail(String email);
}
