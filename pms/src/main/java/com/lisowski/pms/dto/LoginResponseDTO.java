package com.lisowski.pms.dto;

import com.lisowski.pms.security.MyUserDetails;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginResponseDTO {
    private String jwt;
    private String id;
    private String username;
    private List<String> role;

    public LoginResponseDTO(MyUserDetails user, String jwt) {
        this.jwt = jwt;
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
