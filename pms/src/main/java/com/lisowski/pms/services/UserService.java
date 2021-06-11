package com.lisowski.pms.services;

import com.lisowski.pms.dto.UserResponseDTO;
import com.lisowski.pms.payload.UserDataToChangeDTO;
import com.lisowski.pms.payload.UserPasswordRequestDTO;
import com.lisowski.pms.payload.UserRequestDTO;

import java.util.List;

public interface UserService {
    void editUser(UserRequestDTO request, String id);
    void editUserByAdmin(UserDataToChangeDTO request);
    void editPasswordByAdmin(UserPasswordRequestDTO request);
    void editUserPassword(UserPasswordRequestDTO request);
    void deleteUser(String id);
    UserResponseDTO getUser(String id);
    List<UserResponseDTO> getUsers();

}
