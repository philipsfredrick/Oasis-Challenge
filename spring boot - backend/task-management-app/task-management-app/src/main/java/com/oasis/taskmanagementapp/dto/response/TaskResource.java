package com.oasis.taskmanagementapp.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oasis.taskmanagementapp.entities.enums.Status;
import com.oasis.taskmanagementapp.entities.enums.Priority;
import com.oasis.taskmanagementapp.utils.StatusSerializer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResource implements Serializable {

    @Serial
    private static final long serialVersionUID = -3266022396485986760L;

    private Long id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private Priority priority;

//    @JsonSerialize(using = StatusSerializer.class)
    private Status status;

}
