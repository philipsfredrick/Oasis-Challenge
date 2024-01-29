package com.oasis.taskmanagementapp.service;

import com.oasis.taskmanagementapp.converters.TaskToResourceConverter;
import com.oasis.taskmanagementapp.dto.request.TaskRequest;
import com.oasis.taskmanagementapp.dto.response.PaginatedTaskResource;
import com.oasis.taskmanagementapp.dto.response.TaskResource;
import com.oasis.taskmanagementapp.entities.Task;
import com.oasis.taskmanagementapp.entities.enums.Priority;
import com.oasis.taskmanagementapp.entities.enums.Status;
import com.oasis.taskmanagementapp.exception.TaskServiceException;
import com.oasis.taskmanagementapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final CredentialService credentialService;

    private final TaskToResourceConverter taskToResourceConverter;

    private final TaskRepository taskRepository;

    @Transactional
    public TaskResource createTask(TaskRequest taskRequest) {
        try {
        var user = credentialService.getUserAccount();
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .status(taskRequest.getStatus())
                .priority(taskRequest.getPriority())
                .user(user)
                .build();
        taskRepository.save(task);
        return taskToResourceConverter.convert(task);
        } catch (Exception e) {
            log.error(format("An error occurred while creating a task, please contact support. " +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new TaskServiceException("An error occurred while creating a task", INTERNAL_SERVER_ERROR);
        }
    }


    public TaskResource getTaskById(Long taskId) {
        return taskToResourceConverter.convert(taskRepository.findById(taskId).orElseThrow(
                ()-> new TaskServiceException("Task not found")));
    }

    public void updateTask(Long taskId, TaskRequest request) {
        try {
            Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskServiceException("Task not found"));
            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setDueDate(request.getDueDate());
            task.setStatus(request.getStatus());
            task.setPriority(request.getPriority());
            taskRepository.save(task);
        } catch (Exception e) {
            log.error(format("An error occurred while updating task, please contact support. " +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new TaskServiceException("An error occurred while updating a task", INTERNAL_SERVER_ERROR);
        }

    }


    public void updateTaskStatus(Long taskId, Status status) {
        var task = taskRepository.findById(taskId).orElseThrow(()-> new TaskServiceException("Task not found"));
        task.setStatus(status);
        taskRepository.save(task);
    }


    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }


    public Page<Task> retrieveAllTasksByPriorityOrDueDate(Integer page, Integer size, Priority priority, LocalDate dueDate) {
        return taskRepository.findTasksByPriorityOrDueDate(
                priority, dueDate, getPageable(page, size));
    }

    public Page<Task> retrieveAllTasksPriority(Integer page, Integer size, Priority priority) {
        return taskRepository.findTasksByPriority(
                priority, getPageable(page, size));
    }

    public Page<Task> retrieveAllTasksDueDate(Integer page, Integer size, LocalDate dueDate) {
        return taskRepository.findTasksByDueDate(
                dueDate, getPageable(page, size));
    }

    public Page<Task> retrieveAllTasksStatus(Integer page, Integer size, Status status) {
        return taskRepository.findTasksByStatus(
                status, getPageable(page, size));
    }

    public Page<Task> AllTasks(Integer page, Integer size) {
        return taskRepository.findAll(getPageable(page, size));
    }

    public Page<Task> AllTasksByUser(Integer page, Integer size, Long userId) {
        return taskRepository.findTasksByUserId(userId, getPageable(page, size));
    }

    private Pageable getPageable(Integer page, Integer size) {
        size = size < 1 || size > 10 ? 5 : size;
        page = page < 1 ? 1 : page;

        return PageRequest.of(--page, size, Sort.Direction.DESC, "createdAt");
    }
}
