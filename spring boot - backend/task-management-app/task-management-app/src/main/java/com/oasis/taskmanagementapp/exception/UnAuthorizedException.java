package com.oasis.taskmanagementapp.exception;

import com.oasis.taskmanagementapp.dto.ErrorCode;

import java.io.Serial;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class UnAuthorizedException extends OasisException {

    @Serial
    private static final long serialVersionUID = 479871909163568559L;

    public UnAuthorizedException(String msg) {
        super(msg, UNAUTHORIZED);
    }

    public UnAuthorizedException(String msg, ErrorCode errorCode) {
        super(msg, UNAUTHORIZED, errorCode);
    }
}
