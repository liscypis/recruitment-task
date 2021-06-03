package com.lisowski.pms.api;

import com.lisowski.pms.entity.ERole;
import com.lisowski.pms.entity.User;
import com.lisowski.pms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @PostMapping("/api/test/user")
    public ResponseEntity<?> createUser() {
        User u = new User();
        u.setEmail("jakistak@5453.pl");
        u.setUsername("lisek");
        u.setPassword(encoder.encode("test"));
        u.setRole(ERole.ROLE_ADMIN);
        return ResponseEntity.ok(userRepository.save(u));
    }
}
