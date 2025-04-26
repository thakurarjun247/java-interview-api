package project.freedom.authservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/health").permitAll(); // ✅ All public pages like /home/health, /home/signup, etc.
                            auth.requestMatchers("/signup").permitAll(); // ✅ All public pages like /home/health, /home/signup, etc.


                            auth.requestMatchers("/secured").authenticated(); // ✅ Any authenticated user

                           // auth.requestMatchers("/secured/**").hasRole("LOGGED_IN"); // Only allow users with the USER role to access the /user/** endpoint
                          //  auth.requestMatchers("/admin/**").hasRole("ADMIN"); // Only allow users with the ADMIN role to access the /admin/** endpoint
                            auth.anyRequest().authenticated();
                        }
                )
                .csrf(csrf ->csrf.disable())
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .build();
    }
}