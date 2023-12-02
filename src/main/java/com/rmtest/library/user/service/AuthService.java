package com.rmtest.library.user.service;

import com.rmtest.library.exception.CustomException;
import com.rmtest.library.security.TokenProvider;
import com.rmtest.library.user.repository.UserRepository;
import com.rmtest.library.user.dto.AuthRequestDto;
import com.rmtest.library.user.entity.User;
import com.rmtest.library.user.entity.UserRole;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rmtest.library.exception.ErrorCode.*;

@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(TokenProvider tokenProvider, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean signUp(AuthRequestDto.SignUp request) {

        boolean requestUser = userRepository.existsByEmail(request.getEmail());

        if (requestUser) {
            throw new CustomException(ALREADY_REGISTERED_USER);
        }

        String encPassword =
                BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());

        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(encPassword)
                .role(UserRole.GENERAL)
                .build();
        userRepository.save(user);

        return true;
    }

    public String signIn(AuthRequestDto.SignIn request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (!this.passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(UNMATCHED_INFORMATION);
        }

        return tokenProvider
                .generateToken(user.getEmail());
    }
}
