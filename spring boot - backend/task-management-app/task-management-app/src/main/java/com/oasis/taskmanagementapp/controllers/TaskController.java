package com.oasis.taskmanagementapp.controllers;

import com.oasis.taskmanagementapp.dto.request.TaskRequest;
import com.oasis.taskmanagementapp.dto.response.PaginatedTaskResource;
import com.oasis.taskmanagementapp.dto.response.TaskResource;
import com.oasis.taskmanagementapp.entities.enums.Priority;
import com.oasis.taskmanagementapp.entities.enums.Status;
import com.oasis.taskmanagementapp.service.TaskResourceService;
import com.oasis.taskmanagementapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskResourceService taskResourceService;

    @PostMapping(value = "", headers = {"Authorization"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResource> createTask(@Valid @RequestBody TaskRequest request) {
        return new ResponseEntity<>(taskService.createTask(request), CREATED);
    }

    @GetMapping(value = "/{taskId}", headers = {"Authorization"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResource> getATask(@PathVariable("taskId") Long taskId) {
        return new ResponseEntity<>(taskService.getTaskById(taskId), OK);
    }

    @GetMapping(headers = {"Authorization"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginatedTaskResource> viewAllTasks(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return new ResponseEntity<>(taskResourceService.viewAllTasksByUser(page, size), OK);
    }

    @GetMapping("/due_date_priority")
    public ResponseEntity<PaginatedTaskResource> getAllTaskByDueDateOrPriority(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer size,
            @RequestParam(value = "dueDate", required = false) LocalDate dueDate,
            @RequestParam(value = "priority", required = false) Priority priority) {
        return new ResponseEntity<>(taskResourceService.viewAllTasksByPriorityOrDueDate(page, size, priority, dueDate), OK);
    }

    @GetMapping("/priority")
    public ResponseEntity<PaginatedTaskResource> getAllTaskByPriority(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer size,
            @RequestParam(value = "priority", required = false) Priority priority) {
        return new ResponseEntity<>(taskResourceService.viewAllTasksByPriority(page, size, priority), OK);
    }

    @GetMapping("/due_date")
    public ResponseEntity<PaginatedTaskResource> getAllTaskByDueDate(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer size,
            @RequestParam(value = "dueDate", required = false) LocalDate dueDate) {
        return new ResponseEntity<>(taskResourceService.viewAllTasksByDueDate(page, size, dueDate), OK);
    }

    @GetMapping("/status")
    public ResponseEntity<PaginatedTaskResource> getAllTaskByStatus(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer size,
            @RequestParam(value = "status", required = false) Status status ) {

        return new ResponseEntity<>(taskResourceService.viewAllTasksByStatus(page, size, status), OK);
    }


    @PatchMapping(value = "/{taskId}", headers = {"Authorization"})
    public void editTaskDetails(@PathVariable("taskId") Long taskId, @Valid @RequestBody TaskRequest request) {
        taskService.updateTask(taskId, request);
    }

    @DeleteMapping(value = "{taskId}", headers = {"Authorization"})
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }
}
