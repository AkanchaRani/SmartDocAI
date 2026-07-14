package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.LoginRequest;
import com.akancha.smartdocai.dto.LoginResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Login - checks email/password and starts a simple session
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request,
                                             HttpServletRequest httpRequest) {

        LoginResponse response = authService.login(request);

        // Store logged in user in session (simple session based auth)
        httpRequest.getSession().setAttribute("loggedInUser", response);

        return new ApiResponse<>(true, "Login successful", response);
    }

    // Logout - clears the session
    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest httpRequest) {

        httpRequest.getSession().invalidate();

        return new ApiResponse<>(true, "Logout successful", null);
    }
}
