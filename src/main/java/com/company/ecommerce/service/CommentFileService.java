package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CommentFileResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface CommentFileService {
    HttpApiResponse<String> createCommentFile(List<MultipartFile> files, Long commentId) throws IOException;

    HttpApiResponse<List<CommentFileResponseDto>> getAllCommentFilesByCommentId(Long commentId);

    HttpApiResponse<String> deleteFileById(Long fileId);
}
