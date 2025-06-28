package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CommentRequestDto;
import com.company.ecommerce.dto.response.CommentResponseDto;
import com.company.ecommerce.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<CommentResponseDto>> createComment(
            @RequestBody CommentRequestDto dto
    ) {
        HttpApiResponse<CommentResponseDto> response = commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/by-productId/{id}")
    public ResponseEntity<HttpApiResponse<Page<CommentResponseDto>>> getAllCommentByProductId(
            @PathVariable Long id,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer sizNumber
    ) {
        HttpApiResponse<Page<CommentResponseDto>> response
                = commentService.getAllCommentByProductId(id, PageRequest.of(pageNumber, sizNumber));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/by-userId/{id}")
    public ResponseEntity<HttpApiResponse<Page<CommentResponseDto>>> getAllCommentByUserId(
            @PathVariable Long id,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer sizNumber
    ) {
        HttpApiResponse<Page<CommentResponseDto>> response
                = commentService.getAllCommentByUserId(id, PageRequest.of(pageNumber, sizNumber));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpApiResponse<CommentResponseDto>> updateCommentById(
            @RequestBody CommentRequestDto dto, @PathVariable Long id
    ) {
        HttpApiResponse<CommentResponseDto> response = commentService.updateCommentById(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpApiResponse<String>> deleteCommentById(
            @PathVariable Long id
    ) {
        HttpApiResponse<String> response = commentService.deleteCommentById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
