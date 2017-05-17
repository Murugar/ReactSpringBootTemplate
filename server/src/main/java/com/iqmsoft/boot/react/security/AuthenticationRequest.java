package com.iqmsoft.boot.react.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    private String username;
    private String password;
}
