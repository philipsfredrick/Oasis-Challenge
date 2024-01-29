package com.oasis.taskmanagementapp.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource implements Serializable {

    @Serial
    private static final long serialVersionUID = -8048401907608798633L;
    private Long userId;

    private String name;

    private String email;
}
