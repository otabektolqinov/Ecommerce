package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CommentRequestDto;
import com.company.ecommerce.dto.response.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    HttpApiResponse<CommentResponseDto> createComment(CommentRequestDto dto);

    HttpApiResponse<List<CommentResponseDto>> getAllCommentByProductId(Long id);

    HttpApiResponse<List<CommentResponseDto>> getAllCommentByUserId(Long id);

    HttpApiResponse<CommentResponseDto> updateCommentById(CommentRequestDto dto, Long id);

    HttpApiResponse<String> deleteCommentById(Long id);

}
