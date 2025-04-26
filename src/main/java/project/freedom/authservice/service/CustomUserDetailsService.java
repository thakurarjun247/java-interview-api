package project.freedom.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.exception.ExceptionType;
import project.freedom.authservice.exception.UserNotFoundException;
import project.freedom.authservice.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalfetchedUser = userRepository.findByEmail(email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ExceptionType.USER_NOT_FOUND));


            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(Collections.emptyList()) // no roles for now
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();

    }

}

