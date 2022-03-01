package com.railway.biticket.common.exception;

import com.railway.biticket.common.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new Response<>("Validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler( {NotFoundException.class})
    ResponseEntity<Response<?>> notFoundHandler(Exception e) {
        return Response.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build().makeResponseEntity();
    }

    @ExceptionHandler({ConflictException.class})
    ResponseEntity<Response<?>> conflictHandler(Exception e) {
        return Response.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.CONFLICT.value())
                .build().makeResponseEntity();
    }
}
