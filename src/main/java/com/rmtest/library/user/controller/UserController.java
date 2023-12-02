package com.rmtest.library.user.controller;

import com.rmtest.library.user.dto.AuthRequestDto;
import com.rmtest.library.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping()
    public ResponseEntity<Boolean> signUp(@RequestBody AuthRequestDto.SignUp request) {

        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> signIn(@RequestBody AuthRequestDto.SignIn request) {

        return ResponseEntity.ok(authService.signIn(request));
    }
}
