package com.example.test2.errorHandling.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{

    private String message;

    private Integer code;

    public CustomException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, String message1, Integer code) {
        super(message);
        this.message = message1;
        this.code = code;
    }

    public CustomException(String message, Throwable cause, String message1, Integer code) {
        super(message, cause);
        this.message = message1;
        this.code = code;
    }

    public CustomException(Throwable cause, String message, Integer code) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.code = code;
    }
}
