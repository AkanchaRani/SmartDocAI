package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.LoginRequest;
import com.akancha.smartdocai.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
