package com.lisowski.pms.api;


import com.lisowski.pms.dto.LoginResponseDTO;
import com.lisowski.pms.payload.LoginRequestDTO;
import com.lisowski.pms.security.MyUserDetails;
import com.lisowski.pms.services.MyUserDetailsService;
import com.lisowski.pms.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(userDetails, jwt));
    }
}
