package com.example.test2.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppError {

    private String message;

    private Integer code;

    private LocalDateTime time;
}
