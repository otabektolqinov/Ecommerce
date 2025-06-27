package com.company.ecommerce.dto.request;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    private String pluses;
    private String minuses;
    private Integer rating;
    private String body;
    private Long userId;
    private Long productId;


}
