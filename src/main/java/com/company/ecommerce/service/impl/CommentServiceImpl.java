package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.Users;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CommentRequestDto;
import com.company.ecommerce.dto.response.CommentResponseDto;
import com.company.ecommerce.repository.CommentRepository;
import com.company.ecommerce.service.CommentService;
import com.company.ecommerce.service.mapper.CommentMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import com.company.ecommerce.service.validation.CommentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CommentValidation commentValidation;

    @Override
    public HttpApiResponse<CommentResponseDto> createComment(CommentRequestDto dto) {
        Users user = commentValidation.userExists(dto.getUserId());
        if (user == null) {
            return HttpApiResponse.<CommentResponseDto>builder()
                    .success(false)
                    .message("User does not exist")
                    .status(HttpStatus.NOT_FOUND)
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        Product product = commentValidation.productExists(dto.getProductId());
        if (product == null) {
            return HttpApiResponse.<CommentResponseDto>builder()
                    .success(false)
                    .message("Product does not exist")
                    .status(HttpStatus.NOT_FOUND)
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .build();
        }

        Comment entity = commentMapper.toEntity(dto);
        entity.setProduct(product);
        entity.setUsers(user);

        product.getComments().add(entity);
        user.getCommentList().add(entity);

        commentRepository.save(entity);

        return HttpApiResponse.<CommentResponseDto>builder()
                .success(true)
                .message("Comment created successfully")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(commentMapper.toDto(entity))
                .build();
    }


    @Override
    public HttpApiResponse<List<CommentResponseDto>> getAllCommentByProductId(Long id) {
        Product product = commentValidation.productExists(id);
        if (product == null) {
            return ResponseUtils.buildNotFoundResponse("Product", id);
        }
        Optional<List<Comment>> optionalList = commentRepository.findAllByProductIdAndDeletedAtIsNull(id);
        if (optionalList.isEmpty()) {
            return HttpApiResponse.<List<CommentResponseDto>>builder()
                    .success(false)
                    .message("Product list is empty")
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return HttpApiResponse.<List<CommentResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(optionalList.get().stream().map(commentMapper::toDto).toList())
                .build();
    }

    @Override
    public HttpApiResponse<List<CommentResponseDto>> getAllCommentByUserId(Long id) {

        Users user = commentValidation.userExists(id);
        if (user == null) {
            return ResponseUtils.buildNotFoundResponse("User", id);
        }

        Optional<List<Comment>> optionalList = commentRepository.findAllByUsersIdAndDeletedAtIsNull(id);
        if (optionalList.isEmpty()) {
            return HttpApiResponse.<List<CommentResponseDto>>builder()
                    .success(false)
                    .message("Comment list is empty")
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return HttpApiResponse.<List<CommentResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(optionalList.get().stream().map(commentMapper::toDto).toList())
                .build();
    }

    @Override
    public HttpApiResponse<CommentResponseDto> updateCommentById(CommentRequestDto dto, Long id) {

        Optional<Comment> comment = commentRepository.findByIdAndDeletedAtIsNull(id);
        if (comment.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Comment", id);
        }

        Comment updateEntity = commentMapper.updateEntity(dto, comment.get());
        commentRepository.save(updateEntity);

        return HttpApiResponse.<CommentResponseDto>builder()
                .success(true)
                .message("Comment updated successfully")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(commentMapper.toDto(updateEntity))
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findByIdAndDeletedAtIsNull(id);
        if (comment.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Comment", id);
        }

        comment.get().setDeletedAt(LocalDateTime.now());
        commentRepository.save(comment.get());

        return HttpApiResponse.<String>builder()
                .success(true)
                .message("Comment deleted successfully")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }
}
