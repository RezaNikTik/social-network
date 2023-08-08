package com.example.test2.errorHandling.exception;

import jdk.jshell.Snippet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException{

    private String message;

    private Integer code;

    private HttpStatus status;

    public CustomException(String message, Integer code, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public CustomException(String message, String message1, Integer code, HttpStatus status) {
        super(message);
        this.message = message1;
        this.code = code;
        this.status = status;
    }

    public CustomException(String message, Throwable cause, String message1, Integer code, HttpStatus status) {
        super(message, cause);
        this.message = message1;
        this.code = code;
        this.status = status;
    }

    public CustomException(Throwable cause, String message, Integer code, HttpStatus status) {
        super(cause);
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, Integer code, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.code = code;
        this.status = status;
    }
}
