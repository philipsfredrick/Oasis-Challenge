package com.oasis.taskmanagementapp.service;

import com.oasis.taskmanagementapp.converters.TaskToResourceConverter;
import com.oasis.taskmanagementapp.dto.response.PaginatedTaskResource;
import com.oasis.taskmanagementapp.dto.response.TaskResource;
import com.oasis.taskmanagementapp.entities.Task;
import com.oasis.taskmanagementapp.entities.enums.Priority;
import com.oasis.taskmanagementapp.entities.enums.Status;
import com.oasis.taskmanagementapp.exception.TaskServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskResourceService {

    private final TaskService taskService;

    private final CredentialService credentialService;

    private final TaskToResourceConverter taskToResourceConverter;


    @Transactional(readOnly = true)
    public PaginatedTaskResource viewAllTasksByUser(Integer page, Integer size) {
        try {
            var user = credentialService.getUserAccount();
            Page<Task> tasks = taskService.AllTasksByUser(page, size, user.getId());

            List<TaskResource> taskResourceList = tasks.getContent()
                    .parallelStream().map(taskToResourceConverter::convert).toList();
            return PaginatedTaskResource.builder()
                    .taskResources(taskResourceList)
                    .currentPage(tasks.getPageable().getPageNumber() + 1)
                    .totalElements(tasks.getTotalElements())
                    .totalPages(tasks.getTotalPages())
                    .build();
        } catch (Exception e) {
            log.error(format("An error occurred while view task, please contact support" +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new TaskServiceException("An error occurred while viewing tasks, please contact support");
        }
    }


    @Transactional(readOnly = true)
    public PaginatedTaskResource viewAllTasksByPriorityOrDueDate(Integer page, Integer size, Priority priority, LocalDate dueDate) {
        try {
            Page<Task> tasks = taskService.retrieveAllTasksByPriorityOrDueDate(page, size, priority, dueDate);

            List<TaskResource> taskResourceList = tasks.getContent()
                    .parallelStream().map(taskToResourceConverter::convert).toList();
            return PaginatedTaskResource.builder()
                    .taskResources(taskResourceList)
                    .currentPage(tasks.getPageable().getPageNumber() + 1)
                    .totalElements(tasks.getTotalElements())
                    .totalPages(tasks.getTotalPages())
                    .build();
        } catch (Exception e) {
            log.error(format("An error occurred while view task, please contact support" +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new TaskServiceException("An error occurred while viewing tasks, please contact support");
        }
    }

    @Transactional(readOnly = true)
    public PaginatedTaskResource viewAllTasksByPriority(Integer page, Integer size, Priority priority) {
        try {
            Page<Task> tasks = taskService.retrieveAllTasksPriority(page, size, priority);

            List<TaskResource> taskResourceList = tasks.getContent()
                    .parallelStream().map(taskToResourceConverter::convert).toList();
            return PaginatedTaskResource.builder()
                    .taskResources(taskResourceList)
                    .currentPage(tasks.getPageable().getPageNumber() + 1)
                    .totalElements(tasks.getTotalElements())
                    .totalPages(tasks.getTotalPages())
                    .build();
        } catch (Exception e) {
            log.error(format("An error occurred while view task, please contact support" +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new TaskServiceException("An error occurred while viewing tasks, please contact support");
        }
    }

    @Transactional(readOnly = true)
    public PaginatedTaskResource viewAllTasksByDueDate(Integer page, Integer size, LocalDate dueDate) {
        try {
            Page<Task> tasks = taskService.retrieveAllTasksDueDate(page, size, dueDate);

            List<TaskResource> taskResourceList = tasks.getContent()
                    .parallelStream().map(taskToResourceConverter::convert).toList();
            return PaginatedTaskResource.builder()
                    .taskResources(taskResourceList)
                    .currentPage(tasks.getPageable().getPageNumber() + 1)
                    .totalElements(tasks.getTotalElements())
                    .totalPages(tasks.getTotalPages())
                    .build();
        } catch (Exception e) {
            log.error(format("An error occurred while view task, please contact support" +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new TaskServiceException("An error occurred while viewing tasks, please contact support");
        }
    }

    @Transactional(readOnly = true)
    public PaginatedTaskResource viewAllTasksByStatus(Integer page, Integer size, Status status) {
        try {
            Page<Task> tasks = taskService.retrieveAllTasksStatus(page, size, status);

            List<TaskResource> taskResourceList = tasks.getContent()
                    .parallelStream().map(taskToResourceConverter::convert).toList();
            return PaginatedTaskResource.builder()
                    .taskResources(taskResourceList)
                    .currentPage(tasks.getPageable().getPageNumber() + 1)
                    .totalElements(tasks.getTotalElements())
                    .totalPages(tasks.getTotalPages())
                    .build();
        } catch (Exception e) {
            log.error(format("An error occurred while view task, please contact support" +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new TaskServiceException("An error occurred while viewing tasks, please contact support");
        }
    }

}
