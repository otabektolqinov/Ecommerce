package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.TokenResponseDto;
import com.company.ecommerce.dto.request.AuthUserRequestDto;
import com.company.ecommerce.dto.response.AuthUserResponseDto;
import com.company.ecommerce.service.AuthUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserService authUserService;

    @PostMapping("/create-auth-user")
    public ResponseEntity<HttpApiResponse<AuthUserResponseDto>> createAuthUser(@RequestBody @Valid AuthUserRequestDto dto){
        HttpApiResponse<AuthUserResponseDto> response = authUserService.registerAuthUser(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/get-token")
    public ResponseEntity<HttpApiResponse<TokenResponseDto>> getToken(@RequestBody AuthUserRequestDto dto){
        HttpApiResponse<TokenResponseDto> response = authUserService.getToken(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<HttpApiResponse<TokenResponseDto>> refreshToken(@RequestParam(value = "token") String token){
        HttpApiResponse<TokenResponseDto> response = authUserService.refreshToken(token);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
