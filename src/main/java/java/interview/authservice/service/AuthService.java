package java.interview.authservice.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public interface AuthService {
    String logout(HttpServletRequest request, HttpServletResponse response);

    Optional<String> getCurrentUser();
}
