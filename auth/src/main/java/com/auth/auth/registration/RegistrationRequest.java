package com.auth.auth.registration;


import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String email;
    private final String username;
    private final String password;

}
