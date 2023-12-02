package com.rmtest.library.user.dto;

import lombok.Getter;

public class AuthRequestDto {

    @Getter
    public static class SignUp {
        private String userName;
        private String email;
        private String password;
    }

    @Getter
    public static class SignIn {
        private String email;
        private String password;
    }
}
