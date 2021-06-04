package com.lisowski.pms.services;

import com.lisowski.pms.dto.LoginResponseDTO;
import com.lisowski.pms.entity.ERole;
import com.lisowski.pms.entity.User;
import com.lisowski.pms.payload.LoginRequestDTO;
import com.lisowski.pms.payload.UserRequestDTO;
import com.lisowski.pms.repository.UserRepository;
import com.lisowski.pms.security.MyUserDetails;
import com.lisowski.pms.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);

        return new LoginResponseDTO(userDetails, jwt);
    }

    @Override
    public void registerUser(UserRequestDTO request) {
        checkRequest(request);

        User user = modelMapper.map(request, User.class);
        user.setPassword(encoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    private void checkRequest(UserRequestDTO request) {
        checkUserIsExist(userRepository.findByUsername(request.getUsername()), "The username is already in use.");
        checkUserIsExist(userRepository.findByEmail(request.getEmail()), "The email is already in use.");
        checkUserIsExist(userRepository.findByPhoneNumber(request.getPhoneNumber()), "The phone number is already in use.");
        checkPhoneNumber(request.getPhoneNumber());
        checkRole(request);
    }

    private void checkRole(UserRequestDTO request) {
        if (!request.getRole().name().equals(ERole.ROLE_ADMIN.name())
                && !request.getRole().name().equals(ERole.ROLE_USER.name())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid role");
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("[0-9]+"))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid phone number");
    }

    private void checkUserIsExist(Optional<User> optionalUser, String s) {
        if (optionalUser.isPresent()) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, s);
    }

}
