package java.interview.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.interview.authservice.entity.User;
import java.interview.authservice.exception.ExceptionType;
import java.interview.authservice.exception.UserNotFoundException;
import java.interview.authservice.repository.HomeRepository;
import java.interview.authservice.service.HomeService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//if we don't make it annotated, spring wont' be able to find and autowire it.
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    HomeRepository userRepository;
    @Autowired
    @Lazy

    HomeService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Optional<User> optionalUser= userService.findByEmail(email);
        Optional<User> optionalfetchedUser = userService.findByEmail(email);
        User user = optionalfetchedUser
                .orElseThrow(() -> new UserNotFoundException(ExceptionType.USER_NOT_FOUND));
        String userRole = "ROLE_" + user.getUserRoles().stream().toList().get(0).toString();
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRole));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }

}

