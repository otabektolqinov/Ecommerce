package com.company.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "Firstname Cannot be Blank, Null or Empty")
    private String firstName;
    @NotBlank(message = "Lastname Cannot be Blank, Null or Empty")
    private String lastName;
    @NotBlank(message = "PhoneNumber cannot be Blank, Null or Empty")
    private String phoneNumber;
    @NotBlank(message = "Email Cannot be Blank, Null or Empty")
    @Email(message = "Incorrect email format. Please check your email")
    private String email;
    @NotNull(message = "GenderIndex cannot be null")
    private Integer genderIndex;
    @NotNull(message = "Birthday cannot be null")
    private LocalDate birthday;

}
