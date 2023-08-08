package com.example.test2.errorHandling.controllerAdvice;

import com.example.test2.errorHandling.ApiError;
import com.example.test2.errorHandling.AppError;
import com.example.test2.errorHandling.exception.CustomException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler  {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handelInternalService(CustomException e) {
        return new ResponseEntity<Object>(new AppError(e.getMessage(), e.getCode(),LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ApiError>> constraintViolationException(ConstraintViolationException ex) {
        List<ApiError> errors = ex.getConstraintViolations().stream().map(error -> new ApiError(error.getMessage(),
                error.getInvalidValue(),error.getLeafBean().toString())).toList();
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }








}
