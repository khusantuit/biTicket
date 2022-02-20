package com.railway.biticket.common.exception;

import com.railway.biticket.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler( {NotFoundException.class})
    ResponseEntity<Response<?>> notFoundHandler(Exception e) {
        return getResponseEntity(Response.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build(), e);
    }

    @ExceptionHandler({ConflictException.class})
    ResponseEntity<Response<?>> conflictHandler(Exception e) {
        return getResponseEntity(Response.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.CONFLICT.value())
                .build(), e);
    }

    private ResponseEntity<Response<?>> getResponseEntity(
            Response<?> res,
            Exception e
    ) {
        return new ResponseEntity<>(res, HttpStatus.valueOf(res.getStatusCode()));
    }

}
