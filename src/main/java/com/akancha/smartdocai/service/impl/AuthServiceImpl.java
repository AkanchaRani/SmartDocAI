package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.dto.LoginRequest;
import com.akancha.smartdocai.dto.LoginResponse;
import com.akancha.smartdocai.entity.User;
import com.akancha.smartdocai.exception.InvalidCredentialsException;
import com.akancha.smartdocai.repository.UserRepository;
import com.akancha.smartdocai.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Simple login - checks email and password from the users table
    // NOTE: Password is stored as plain text for now (matches User Management module).
    // This can be replaced with BCryptPasswordEncoder + JWT later.
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(request.getEmail()))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
