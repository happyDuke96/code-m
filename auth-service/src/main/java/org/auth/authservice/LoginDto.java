package org.auth.authservice;

import lombok.Value;

@Value
public class LoginDto {
    String username;
    String password;
}
