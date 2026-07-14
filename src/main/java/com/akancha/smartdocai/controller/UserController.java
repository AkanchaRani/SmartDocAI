package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.UserRequest;
import com.akancha.smartdocai.dto.UserResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User
    @PostMapping
    public ApiResponse<UserResponse> saveUser(@Valid @RequestBody UserRequest request) {

        UserResponse user = userService.saveUser(request);

        return new ApiResponse<>(
                true,
                "User created successfully",
                user
        );
    }

    // Get All Users
    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        return new ApiResponse<>(
                true,
                "Users fetched successfully",
                users
        );
    }

    // Get User By Id
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {

        UserResponse user = userService.getUserById(id);

        return new ApiResponse<>(
                true,
                "User fetched successfully",
                user
        );
    }

    // Update User
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        UserResponse user = userService.updateUser(id, request);

        return new ApiResponse<>(
                true,
                "User updated successfully",
                user
        );
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return new ApiResponse<>(
                true,
                "User deleted successfully",
                null
        );
    }
}