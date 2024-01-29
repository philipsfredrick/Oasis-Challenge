package com.oasis.taskmanagementapp.exception;

import com.oasis.taskmanagementapp.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class TaskServiceException extends OasisException {

    public TaskServiceException(String msg) {
        super(msg);
    }

    public TaskServiceException(String msg, HttpStatus status) {
        super(msg, status);
    }

    public TaskServiceException(String msg, HttpStatus status, ErrorCode errorCode) {
        super(msg, status, errorCode);
    }
}
