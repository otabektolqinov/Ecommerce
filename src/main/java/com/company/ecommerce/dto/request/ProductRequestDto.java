package com.company.ecommerce.dto.request;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.domain.ProductFile;
import com.company.ecommerce.domain.Seller;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "Product nama cannot be empty or null")
    private String name;

    @NotBlank(message = "Description nama cannot be empty or null")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    @NotNull(message = "SellerId cannot be null")
    @Positive(message = "SellerId must be greater than zero")
    private Long sellerId;

    @NotNull(message = "CategoryId cannot be null")
    @Positive(message = "CategoryId must be greater than zero")
    private Long categoryId;

}
