package com.oasis.taskmanagementapp.exception;

import com.oasis.taskmanagementapp.dto.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class OasisException extends RuntimeException {

    protected HttpStatus status;

    private ErrorCode errorCode;

    private String metadata;

    private String infoLink;

    public OasisException(final String msg) {
        super(msg);
    }

    public OasisException(final String msg, final HttpStatus status) {
        this(msg);
        this.status = status;
    }

    public OasisException(final String msg, final HttpStatus status, final ErrorCode errorCode) {
        this(msg, status);
        this.errorCode = errorCode;
    }

    public OasisException(final String msg, final HttpStatus status, final String metadata) {
        this(msg, status);
        this.metadata = metadata;
    }

    public OasisException(final String msg, final HttpStatus status, final ErrorCode errorCode, final String metadata) {
        this(msg, status, errorCode);
        this.metadata = metadata;
    }
}
