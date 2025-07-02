package com.company.ecommerce.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    private String pluses;
    private String minuses;
    @NotBlank(message = "Rating cannot be empty or null")
    private Integer rating;
    @NotBlank(message = "Comment text cannot be blank, empty or null")
    private String body;
    @NotNull(message = "UserId cannot be null")
    @Positive(message = "UserId must be greater than 0")
    private Long userId;
    @Positive(message = "ProductId must be greater than 0")
    private Long productId;


}
