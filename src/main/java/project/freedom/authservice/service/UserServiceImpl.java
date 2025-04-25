package project.freedom.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.exception.DuplicateEmailException;
import project.freedom.authservice.exception.ExceptionType;
import project.freedom.authservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        //no need to duplicate email check, jpa will do it, look at the annotation on email
        //field in user class
        if(isDuplicateEmail(user.getEmail())) throw new DuplicateEmailException(ExceptionType.DUPLICATE_EMAIL);
        return userRepository.save(user);

    }

    private boolean isDuplicateEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

