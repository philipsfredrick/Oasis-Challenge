package com.oasis.taskmanagementapp.converters;

import com.oasis.taskmanagementapp.dto.response.TaskResource;
import com.oasis.taskmanagementapp.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskToResourceConverter {

    public TaskResource convert(Task task) {
        return TaskResource.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .status(task.getStatus())
                .build();
    }
}
