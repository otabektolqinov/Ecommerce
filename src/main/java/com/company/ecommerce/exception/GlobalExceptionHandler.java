package com.company.ecommerce.exception;

import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.dto.HttpApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<HttpApiResponse<Void>> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return ResponseEntity.status (HttpStatus.NOT_FOUND)
                .body (HttpApiResponse.<Void>builder ()
                        .responseCode (HttpStatus.NOT_FOUND.value ())
                        .message (e.getMessage ())
                        .status (HttpStatus.NOT_FOUND)
                        .build ());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpApiResponse<Void>> methodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ){
        List<ErrorDto> errors = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError error: fieldErrors){
            String field = error.getField();
            String message = error.getDefaultMessage();
            String rejectionValue = String.valueOf(error.getRejectedValue());
            errors.add(new ErrorDto(
                    field,
                    String.format("%s, Rejection value %s", message, rejectionValue),
                    HttpStatus.BAD_REQUEST.value()
            ));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                HttpApiResponse.<Void>builder()
                        .message("Validation Failed")
                        .errors(errors)
                        .build()
        );
    }

}
