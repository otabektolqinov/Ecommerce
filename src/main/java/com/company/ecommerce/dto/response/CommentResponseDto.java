package com.company.ecommerce.dto.response;

import com.company.ecommerce.domain.CommentFile;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long userId;
    private String pluses;
    private String minuses;
    private Integer rating;
    private Long productId;
    private List<CommentFileResponseDto> commentFileList;
}
