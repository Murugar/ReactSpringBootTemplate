package com.iqmsoft.boot.react.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtAuthenticationResponse {
    private String token;
    private String username;
}
