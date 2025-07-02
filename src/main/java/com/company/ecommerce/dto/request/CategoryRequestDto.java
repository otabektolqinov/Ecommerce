package com.company.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {
    @NotBlank(message = "CategoryName cannot be empty or null")
    private String name;

}
