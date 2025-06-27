package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CommentFileResponseDto;
import com.company.ecommerce.dto.response.ProductFileResponseDto;
import com.company.ecommerce.service.CommentFileService;
import com.company.ecommerce.service.ProductFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commentFile")
public class CommentFileController {


    private final CommentFileService commentFileService;

    @PostMapping(value = "/{commentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpApiResponse<String>> uploadFileToComment(
            @RequestPart List<MultipartFile> files,
            @PathVariable Long commentId) throws IOException {

        HttpApiResponse<String> response = commentFileService.createCommentFile(files, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/get-all/{commentId}")
    public ResponseEntity<HttpApiResponse<List<CommentFileResponseDto>>> getAllCommentFilesByCommentId(@PathVariable Long commentId) {
        HttpApiResponse<List<CommentFileResponseDto>> response = commentFileService.getAllCommentFilesByCommentId(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{fileId}")
    public ResponseEntity<HttpApiResponse<String>> deleteFileById(@PathVariable Long fileId) {
        HttpApiResponse<String> response = commentFileService.deleteFileById(fileId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
