package com.lisowski.pms.services;

import com.lisowski.pms.entity.User;
import com.lisowski.pms.repository.UserRepository;
import com.lisowski.pms.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("not found " + s));
        return new MyUserDetails(user);
    }
}
