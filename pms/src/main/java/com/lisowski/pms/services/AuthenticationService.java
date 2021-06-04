package com.lisowski.pms.services;

import com.lisowski.pms.dto.LoginResponseDTO;
import com.lisowski.pms.payload.LoginRequestDTO;
import com.lisowski.pms.payload.UserRequestDTO;

public interface AuthenticationService {
    LoginResponseDTO login(LoginRequestDTO request);
    void registerUser(UserRequestDTO request);
}
