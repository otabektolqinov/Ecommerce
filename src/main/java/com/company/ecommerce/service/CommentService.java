package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CommentRequestDto;
import com.company.ecommerce.dto.response.CommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    HttpApiResponse<CommentResponseDto> createComment(CommentRequestDto dto);

    HttpApiResponse<Page<CommentResponseDto>> getAllCommentByProductId(Long id, Pageable pageable);

    HttpApiResponse<Page<CommentResponseDto>> getAllCommentByUserId(Long id, Pageable pageable);

    HttpApiResponse<CommentResponseDto> updateCommentById(CommentRequestDto dto, Long id);

    HttpApiResponse<String> deleteCommentById(Long id);

}
