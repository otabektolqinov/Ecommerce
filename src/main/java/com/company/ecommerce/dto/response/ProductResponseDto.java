package com.company.ecommerce.dto.response;

import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.domain.ProductFile;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Float rating;
    private Long sellerId;
    private Long categoryId;
    private List<CommentResponseDto> comments;
    private List<ProductFileResponseDto> productFiles;
}
