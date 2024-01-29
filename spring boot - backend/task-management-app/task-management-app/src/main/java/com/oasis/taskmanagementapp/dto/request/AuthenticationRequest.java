package com.oasis.taskmanagementapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5229821592486293495L;

    @NotBlank(message = "email must not be blank")
    @Email(message = "please enter a valid email")
    private String email;

    @NotBlank(message = "password must not be blank")
    private String password;
}
