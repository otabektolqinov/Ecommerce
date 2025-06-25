package com.company.ecommerce.dto.response;

import com.company.ecommerce.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private LocalDate birthday;

}
