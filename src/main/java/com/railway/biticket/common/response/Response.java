package com.railway.biticket.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"message", "status","data"})
public class Response<T> {
    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "status")
    private Integer statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data")
    private T data;

    public Response(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResponseEntity<Response<?>> makeResponseEntity() {
        return new ResponseEntity<>(this, HttpStatus.valueOf(this.statusCode));
    }
}
