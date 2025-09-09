package com.company.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMvcResponseDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Float rating;
    private SellerResponseDto seller;
    private CategoryResponseDto category;
}
