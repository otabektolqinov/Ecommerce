package com.company.ecommerce.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Integer genderIndex;
    private LocalDate birthday;

}
