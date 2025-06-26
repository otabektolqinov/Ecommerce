package com.company.ecommerce.dto.request;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.domain.ProductFile;
import com.company.ecommerce.domain.Seller;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Long sellerId;
    private Long categoryId;

}
