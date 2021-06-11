package com.lisowski.pms.services.servicesImpl;

import com.lisowski.pms.dto.UserResponseDTO;
import com.lisowski.pms.entity.User;
import com.lisowski.pms.payload.UserDataToChangeDTO;
import com.lisowski.pms.payload.UserPasswordRequestDTO;
import com.lisowski.pms.payload.UserRequestDTO;
import com.lisowski.pms.repository.UserRepository;
import com.lisowski.pms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.lisowski.pms.services.servicesImpl.AuthenticationServiceImpl.checkPhoneNumber;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;

    @Override
    public void editUser(UserRequestDTO request, String id) {
        User user = getUserFromDB(id);
        checkRequest(request, user);

        checkPassword(request.getPassword(), user);

        request.setPassword(encoder.encode(request.getPassword()));
        modelMapper.map(request, user);

        userRepository.save(user);
    }

    @Override
    public void editUserByAdmin(UserDataToChangeDTO request) {
        User user = getUserFromDB(request.getId());
        checkRequest(modelMapper.map(request, UserRequestDTO.class), user);
        modelMapper.map(request, user);
        userRepository.save(user);
    }

    @Override
    public void editPasswordByAdmin(UserPasswordRequestDTO request) {
        User user = getUserFromDB(request.getId());
        request.setNewPassword(encoder.encode(request.getNewPassword()));
        user.setPassword(request.getNewPassword());

        userRepository.save(user);
    }

    @Override
    public void editUserPassword(UserPasswordRequestDTO request) {
        User user = getUserFromDB(request.getId());
        checkPassword(request.getOldPassword(), user);
        request.setNewPassword(encoder.encode(request.getNewPassword()));
        user.setPassword(request.getNewPassword());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = getUserFromDB(id);
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO getUser(String id) {
        User user = getUserFromDB(id);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper
                .map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    private User getUserFromDB(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid id"));
    }

    private void checkPassword(String password, User user) {
        if(!encoder.matches(password, user.getPassword()))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Password not matches");
    }

    private void checkRequest(UserRequestDTO request, User user) {
        checkUserIsExist(user, userRepository.findByUsername(request.getUsername()), "Username is taken");
        checkUserIsExist(user, userRepository.findByEmail(request.getEmail()), "Email is taken");
        checkUserIsExist(user, userRepository.findByPhoneNumber(request.getPhoneNumber()), "phone number is taken");
        checkPhoneNumber(request.getPhoneNumber());
    }

    private void checkUserIsExist(User user, Optional<User> optionalUser, String s) {
        optionalUser.ifPresent(u -> {
            if (!u.equals(user)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, s);
            }
        });
    }
}
