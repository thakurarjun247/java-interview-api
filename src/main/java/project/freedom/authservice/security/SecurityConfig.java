package project.freedom.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import project.freedom.authservice.service.CustomUserDetailsService;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Autowire CustomUserDetailsService
    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    // AuthenticationManager bean definition
    // AuthenticationManager bean definition
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        // Create AuthenticationManagerBuilder
        AuthenticationManagerBuilder authenticationManagerBuilder =
                new AuthenticationManagerBuilder(httpSecurity.getSharedObject(AuthenticationConfiguration.class).getObjectPostProcessor());

        // Set CustomUserDetailsService for authentication
        authenticationManagerBuilder.userDetailsService(customUserDetailsService);

        return authenticationManagerBuilder.build();
    }

    // AuthenticationManager bean definition
    @Bean
    public AuthenticationManager authenticationManager1(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Get the AuthenticationManager from the AuthenticationConfiguration
        AuthenticationManagerBuilder authenticationManagerBuilder =
                authenticationConfiguration.getAuthenticationManagerBuilder();

        // Set CustomUserDetailsService for authentication
        authenticationManagerBuilder.userDetailsService(customUserDetailsService);

        return authenticationManagerBuilder.build();
    }

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