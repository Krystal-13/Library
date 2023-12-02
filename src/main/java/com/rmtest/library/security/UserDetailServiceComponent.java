package com.rmtest.library.security;

import com.rmtest.library.user.entity.User;
import com.rmtest.library.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceComponent implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailServiceComponent(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByEmail(username)
                                        .orElseThrow(RuntimeException::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .authorities(user.getRole().toString())
                .build();
    }
}
