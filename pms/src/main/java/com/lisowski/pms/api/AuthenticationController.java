package com.lisowski.pms.api;


import com.lisowski.pms.payload.LoginRequestDTO;
import com.lisowski.pms.payload.UserRequestDTO;
import com.lisowski.pms.services.servicesImpl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequestDTO request){
        authenticationService.registerUser(request);
        return ResponseEntity.ok().build();
    }
}
