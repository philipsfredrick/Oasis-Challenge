package com.oasis.taskmanagementapp.converters;

import com.oasis.taskmanagementapp.dto.response.UserResource;
import com.oasis.taskmanagementapp.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Builder
@Component
public class UserToResourceConverter {

    public UserResource convert(User user) {
        return UserResource.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
