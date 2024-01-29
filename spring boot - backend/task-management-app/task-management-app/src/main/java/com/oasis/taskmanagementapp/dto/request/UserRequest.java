package com.oasis.taskmanagementapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1324481650234493094L;

    @NotBlank(message = "name must not be blank")
    @JsonProperty("name")
    private String name;

    @Email(message = "please enter a valid email")
    @NotBlank(message = "email must not be blank")
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(min = 6, message = "password length must not be less than 6")
    private String password;

}
