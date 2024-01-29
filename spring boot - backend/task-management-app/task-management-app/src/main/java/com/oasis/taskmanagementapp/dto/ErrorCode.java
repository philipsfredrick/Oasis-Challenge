package com.oasis.taskmanagementapp.dto;

public enum ErrorCode {

    TASK_UPDATE_FAILED,
    CONSTRAINT_VIOLATION,
    TASK_DOES_NOT_EXIST,
    ACCOUNT_DOES_NOT_EXIST,
    EMAIL_ALREADY_IN_USE,
    INVALID_CREDENTIALS,
    INVALID_INPUT_PROVIDED,
    INVALID_TOKEN,
    MISSING_TOKEN,
    TOKEN_EXPIRED
}