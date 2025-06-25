package com.company.ecommerce.service.utils;


import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.dto.HttpApiResponse;
import org.springframework.http.HttpStatus;

public final class ResponseUtils {

    private ResponseUtils() {
        // Private constructor to prevent instantiation
    }

    public static <T> HttpApiResponse<T> buildNotFoundResponse(String entityName, Long id) {
        String message = String.format("%s with id %d not found", entityName, id);
        return HttpApiResponse.<T>builder()
                .success(false)
                .responseCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .message(message)
                .errors(new ErrorDto(
                        entityName.toLowerCase(),
                        message,
                        HttpStatus.NOT_FOUND.value()
                ))
                .build();
    }
}
