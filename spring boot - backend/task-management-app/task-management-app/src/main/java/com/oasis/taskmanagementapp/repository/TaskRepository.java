package com.oasis.taskmanagementapp.repository;

import com.oasis.taskmanagementapp.entities.Task;
import com.oasis.taskmanagementapp.entities.enums.Status;
import com.oasis.taskmanagementapp.entities.enums.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findTasksByPriorityOrDueDate(Priority priority,
                                             LocalDate dueDate,
                                             Pageable pageable);

    Page<Task> findTasksByUserId(Long userId, Pageable pageable);

    Page<Task> findTasksByPriority(Priority priority, Pageable pageable);

    Page<Task> findTasksByDueDate(LocalDate dueDate, Pageable pageable);

    Page<Task> findTasksByStatus(Status status, Pageable pageable);


}
