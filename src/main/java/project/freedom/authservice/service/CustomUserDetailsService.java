package project.freedom.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.exception.ExceptionType;
import project.freedom.authservice.exception.UserNotFoundException;
import project.freedom.authservice.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
//if we don't make it annotated, spring wont' be able to find and autowire it.
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Optional<User> optionalUser= userService.findByEmail(email);
        Optional<User> optionalfetchedUser = userService.findByEmail(email);
        User user = optionalfetchedUser
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

