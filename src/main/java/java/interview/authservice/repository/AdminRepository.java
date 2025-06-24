package java.interview.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.interview.authservice.entity.User;

import java.util.Optional;

@Repository //// <-- Optional, but helpful
//No, it's optional when you extend JpaRepository, because Spring Boot automatically detects and registers all Spring Data repositories.
//
//But adding @Repository has benefits:
//
//Makes your intent explicit.
//
//Enables better exception translation — Spring wraps JPA exceptions into DataAccessException.
public interface AdminRepository extends JpaRepository<User, Long> {

    //How it works behind the scenes:
    //At startup, Spring scans for interfaces that extend JpaRepository.
    //
    //It creates a proxy class implementing that interface.
    //
    //That proxy delegates calls to a class like SimpleJpaRepository, which has the actual logic using EntityManager.
    //we get these methods for free:
    //save(User entity)
    //
    //findById(Long id)
    //
    //findAll()
    //
    //delete(User entity)
    //
    //existsById(Long id)
    //
    //count()
    //
    //And many more…

    //Spring Data JPA will auto-generate the implementation based on method naming.
    Optional<User> findByEmail(String email);
    // Deletes a user by email and returns number of rows affected (optional)
    void deleteByEmail(String email);

    // Deletes all users from the table
    void deleteAll(); // already inherited from JpaRepository, adding here for clarity


}
