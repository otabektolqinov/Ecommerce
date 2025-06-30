package com.company.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotNull(message = "Firstname cannot be null")
    @NotBlank(message = "Firstname Cannot be Blank")
    private String firstName;
    @NotNull(message = "LastName cannot be null")
    @NotBlank(message = "Lastname Cannot be Blank")
    private String lastName;
    @NotNull(message = "PhoneNumber cannot be null")
    @NotBlank(message = "PhoneNumber cannot be Blank")
    private String phoneNumber;
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email Cannot be Blank")
    @Email(message = "Incorrect email format. Please check your email")
    private String email;
    @NotNull(message = "GenderIndex cannot be null")
    private Integer genderIndex;
    @NotNull(message = "Birthday cannot be null")

    private LocalDate birthday;

}
