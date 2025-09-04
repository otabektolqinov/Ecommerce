package com.company.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserResponseDto {

    private String phoneNumber;
    private String state;
    private String role;
    private Long userId;

}
