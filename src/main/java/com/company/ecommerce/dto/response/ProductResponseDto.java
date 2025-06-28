package com.company.ecommerce.dto.response;

import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.domain.ProductFile;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Float rating;
    private Long sellerId;
    private Long categoryId;
    private Set<CommentResponseDto> comments;
    private Set<ProductFileResponseDto> productFiles;
}
