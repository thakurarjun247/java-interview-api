package backend.interview.api.security;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity


                // 1. Disable CSRF for simple API interaction from a different origin (like React FE)
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())

                // 2. Allow all requests without requiring authentication or authorization
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                // 3. Configure session management (optional for stateless APIs, but CSRF implicitly uses it if enabled)
                // We keep it here as default, but it won't impact your current goal significantly.
                .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Often preferred for REST APIs
                        // .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Your previous setting
                        // .invalidSessionUrl("/invalid-session") // Not needed if session is stateless or not redirected
                )
                // 4. (Optional but good practice for APIs) Disable HTTP Basic or Form Login if they are implicitly enabled
                // or if you had customizers for them that you don't need.
                // .httpBasic(Customizer.withDefaults()) // If you had this, consider removing or disabling
                // .formLogin(Customizer.withDefaults()) // If you had this, consider removing or disabling
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000")); // 👈 Your React dev server origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // 👈 Important if you're using cookies or sessions

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}