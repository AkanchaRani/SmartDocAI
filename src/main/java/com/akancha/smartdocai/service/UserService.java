package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.UserRequest;
import com.akancha.smartdocai.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse saveUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);
}