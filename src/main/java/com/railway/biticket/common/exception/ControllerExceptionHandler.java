package com.railway.biticket.common.exception;

import com.railway.biticket.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler( {NotFoundException.class})
    Response<?> notFoundHandler(Exception e) {
        return Response.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler( {ConflictException.class})
    Response<?> conflictHandler(Exception e) {
        return Response.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.CONFLICT.value())
                .build();
    }

}
