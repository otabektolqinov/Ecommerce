package com.company.ecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserRequestDto {

    private String phoneNumber;
    private String password;

}
