package com.oasis.taskmanagementapp.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oasis.taskmanagementapp.entities.enums.Status;
import com.oasis.taskmanagementapp.entities.enums.Priority;
import com.oasis.taskmanagementapp.utils.LocalDateSerializer;
import com.oasis.taskmanagementapp.utils.StatusDeserializer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3318818310404906028L;

    @NotBlank(message = "title must not be blank")
    private String title;

    @NotBlank(message = "description must not be blank")
    private String description;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateSerializer.class)
    private LocalDate dueDate;

    @JsonDeserialize(using = StatusDeserializer.class)
    private Status status;

    private Priority priority;

}
