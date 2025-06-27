package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.domain.CommentFile;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CommentFileResponseDto;
import com.company.ecommerce.dto.response.ProductFileResponseDto;
import com.company.ecommerce.repository.CommentFileRepository;
import com.company.ecommerce.repository.CommentRepository;
import com.company.ecommerce.service.CommentFileService;
import com.company.ecommerce.service.ProductFileService;
import com.company.ecommerce.service.S3Service;
import com.company.ecommerce.service.mapper.CommentFileMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import com.company.ecommerce.service.validation.CommentFileValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentFileServiceImpl implements CommentFileService {

    private final CommentFileRepository commentFileRepository;
    private final CommentRepository commentRepository;
    private final CommentFileMapper commentFileMapper;
    private final CommentFileValidation commentFileValidation;
    private final S3Service s3Service;


    @Override
    public HttpApiResponse<String> createCommentFile(List<MultipartFile> files, Long commentId) throws IOException {
        Optional<Comment> comment = commentRepository.findByIdAndDeletedAtIsNull(commentId);
        if (comment.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Comment", commentId);
        }
        List<CommentFile> commentFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            String generatedName = file.getOriginalFilename() + UUID.randomUUID();
            String url = s3Service.uploadFile(file.getBytes(), file.getContentType(), generatedName);

            CommentFile commentFile = CommentFile.builder()
                    .contentUrl(url)
                    .originalName(file.getOriginalFilename())
                    .generatedName(generatedName)
                    .comment(comment.get())
                    .size(file.getSize())
                    .mimeType(file.getContentType())
                    .build();
            commentFiles.add(commentFile);
        }

        commentFileRepository.saveAll(commentFiles);
        comment.get().getCommentFileList().addAll(commentFiles);

        return HttpApiResponse.<String>builder()
                .success(true)
                .message("CommentFiles has been successfully created")
                .responseCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public HttpApiResponse<List<CommentFileResponseDto>> getAllCommentFilesByCommentId(Long commentId) {
        Optional<Comment> comment = commentRepository.findByIdAndDeletedAtIsNull(commentId);
        if (comment.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Comment", commentId);
        }
        List<CommentFile> commentFileList = commentFileRepository.findByCommentIdAndDeletedAtIsNull(commentId);

        return HttpApiResponse.<List<CommentFileResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(commentFileList.stream().map(commentFileMapper::toResponseDto).collect(Collectors.toList()))
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteFileById(Long fileId) {
        Optional<CommentFile> commentFile = commentFileRepository.findByIdAndDeletedAtIsNull(fileId);
        if (commentFile.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("CommentFile", fileId);
        }
        commentFile.get().setDeletedAt(LocalDateTime.now());
        commentFileRepository.save(commentFile.get());
        return HttpApiResponse.<String>builder()
                .success(true)
                .message("CommentFiles has been successfully deleted")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }
}
