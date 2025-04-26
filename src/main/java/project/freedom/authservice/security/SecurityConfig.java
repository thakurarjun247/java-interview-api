package project.freedom.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import project.freedom.authservice.service.CustomUserDetailsService;


import static org.springframework.security.config.Customizer.withDefaults;

// @Configuration on a class, we are telling Spring:
//"Hey Spring, this class will define some beans (objects) that you should manage yourself."
//✅So Spring will scan that class at startup
//It will read and create all the objects (beans) defined inside
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired UserDetailsService userDetailsService;
// @Bean  public UserDetailsService userDetailsService(){
//     return
//new CustomUserDetailsService();
// }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // AuthenticationManager bean definition

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
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