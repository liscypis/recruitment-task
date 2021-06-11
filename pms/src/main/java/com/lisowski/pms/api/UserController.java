package com.lisowski.pms.api;

import com.lisowski.pms.payload.UserDataToChangeDTO;
import com.lisowski.pms.payload.UserPasswordRequestDTO;
import com.lisowski.pms.payload.UserRequestDTO;
import com.lisowski.pms.services.servicesImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserRequestDTO request, @RequestParam("userid") String userid) {
        userService.editUser(request, userid);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/userByAdmin")
    public ResponseEntity<?> editUserByAdmin(@Valid @RequestBody UserDataToChangeDTO request) {
        userService.editUserByAdmin(request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/userPasswordByAdmin")
    public ResponseEntity<?> editPasswordByAdmin(@Valid @RequestBody UserPasswordRequestDTO request) {
        userService.editPasswordByAdmin(request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/user")
    public ResponseEntity<?> editPassword(@Valid @RequestBody UserPasswordRequestDTO request) {
        userService.editUserPassword(request);
        return ResponseEntity.ok().build();
    }
}
