package com.zhang.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResult {

    private int status = 400;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ErrorResult() {
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResult error(String message) {
        ErrorResult error = new ErrorResult();
        error.setMessage(message);
        return error;
    }

    public static ErrorResult error(int status, String message) {
        ErrorResult error = new ErrorResult();
        error.setStatus(status);
        error.setMessage(message);
        return error;
    }
}
