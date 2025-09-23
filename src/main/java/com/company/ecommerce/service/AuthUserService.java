package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.TokenResponseDto;
import com.company.ecommerce.dto.request.AuthUserRequestDto;
import com.company.ecommerce.dto.response.AuthUserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserService {

    HttpApiResponse<AuthUserResponseDto> registerAuthUser(AuthUserRequestDto dto);

    HttpApiResponse<TokenResponseDto> getToken(AuthUserRequestDto dto);

    HttpApiResponse<TokenResponseDto> refreshToken(String token);
}
