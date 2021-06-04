package com.lisowski.pms.api;

import com.lisowski.pms.payload.UserPasswordRequestDTO;
import com.lisowski.pms.payload.UserRequestDTO;
import com.lisowski.pms.services.servicesImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PutMapping("/api/test/user")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserRequestDTO request, @RequestParam("userid") String userid) {
        userService.editUser(request, userid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/test/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/api/test/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/api/test/user")
    public ResponseEntity<?> editPassword(@Valid @RequestBody UserPasswordRequestDTO request) {
        userService.editUserPassword(request);
        return ResponseEntity.ok().build();
    }
}
