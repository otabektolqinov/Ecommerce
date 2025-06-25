package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.UserRequestDto;
import com.company.ecommerce.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    HttpApiResponse<UserResponseDto> createUser(UserRequestDto dto);
    HttpApiResponse<UserResponseDto> getUsersById(Long id);
    HttpApiResponse<UserResponseDto> updateUserById(Long id, UserRequestDto dto);
    HttpApiResponse<String> deleteUserById(Long id);

}
