package com.oasis.taskmanagementapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -2451796101212236288L;

    private String message;
}
