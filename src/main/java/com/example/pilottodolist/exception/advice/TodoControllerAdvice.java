package com.example.pilottodolist.exception.advice;

import com.example.pilottodolist.exception.NotFoundException;
import com.example.pilottodolist.exception.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ConcurrentModificationException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class TodoControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse TodoNotFoundExceptionHandler(NotFoundException e) {

        log.error("TodoHandlerExceptionHandler : {} / {}",
                e.getErrorCode().getHttpStatus().value(), e.getErrorCode().getMsg());

        return ErrorResponse.builder()
                .code(e.getErrorCode().getHttpStatus().value())
                .status(e.getErrorCode().getHttpStatus().getReasonPhrase())
                .msg(e.getErrorCode().getMsg())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponse BindExceptionHandler(BindException e) {

        log.error("BindExceptionHandler : {} / {} ", e.getTarget() , e.getFieldError().getDefaultMessage());

        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .msg(e.getFieldError().getDefaultMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse ObjectOptimisticLockingFailureExceptionHandler(ObjectOptimisticLockingFailureException e) {

        log.error("ObjectOptimisticLockingFailureExceptionHandler : {} / {}",
                e.getCause() , e.getMessage());

        return ErrorResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .msg(e.getMessage())
                .build();
    }
}
