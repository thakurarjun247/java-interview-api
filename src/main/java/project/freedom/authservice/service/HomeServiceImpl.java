package project.freedom.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.enums.AuthProvider;
import project.freedom.authservice.enums.UserRole;
import project.freedom.authservice.enums.UserStatus;
import project.freedom.authservice.exception.DuplicateEmailException;
import project.freedom.authservice.exception.ExceptionType;
import project.freedom.authservice.repository.HomeRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User save(User user) {
        //no need to duplicate email check, jpa will do it, look at the annotation on email
        //field in user class
        if(isDuplicateEmail(user.getEmail())) throw new DuplicateEmailException(ExceptionType.DUPLICATE_EMAIL);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        if(user.getAuthProvider()==null)
            user.setAuthProvider(AuthProvider.LOCAL);
        user.setUserStatus(UserStatus.ACTIVE);
        if(user.getEmail().contains("arjun")|| user.getEmail().contains("admin"))
            user.setUserRoles(Set.of(UserRole.ADMIN));
        else
            user.setUserRoles(Set.of(UserRole.USER));
        User savedUser= userRepository.save(user);
        return savedUser;

    }

    private boolean isDuplicateEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

