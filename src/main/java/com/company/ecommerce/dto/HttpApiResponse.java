package com.company.ecommerce.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpApiResponse<T> {

    private boolean success;
    private String message;
    private int responseCode;
    private HttpStatus status;
    private T content;
    private ErrorDto errors;
}
