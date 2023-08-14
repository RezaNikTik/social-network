package com.example.test2.errorHandling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.Path;

@Getter
@Setter
@NoArgsConstructor
public class ApiError {

    private String message;

    private Object typeError;

    private Object path;

    public ApiError(String message, Object typeError, Object path) {
        this.message = message;
        this.typeError = typeError;
        this.path = path;
    }
}
