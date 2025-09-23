package com.company.ecommerce.service.impl;

import com.company.ecommerce.config.JwtTokenFilter;
import com.company.ecommerce.config.JwtUtil;
import com.company.ecommerce.domain.AuthUser;
import com.company.ecommerce.domain.Users;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.TokenResponseDto;
import com.company.ecommerce.dto.request.AuthUserRequestDto;
import com.company.ecommerce.dto.response.AuthUserResponseDto;
import com.company.ecommerce.enums.AuthRole;
import com.company.ecommerce.enums.AuthState;
import com.company.ecommerce.repository.AuthUserRepository;
import com.company.ecommerce.repository.UserRepository;
import com.company.ecommerce.service.AuthUserService;
import com.company.ecommerce.service.mapper.AuthUserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authRepository;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public HttpApiResponse<AuthUserResponseDto> registerAuthUser(AuthUserRequestDto dto) {
        AuthUser entity = authUserMapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setAuthState(AuthState.INACTIVE);
        entity.setRole(AuthRole.USER);
        AuthUser user = authRepository.save(entity);
        return HttpApiResponse.<AuthUserResponseDto>builder()
                .responseCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .success(true)
                .message("OK")
                .content(authUserMapper.toDto(user))
                .build();
    }

    @Override
    public HttpApiResponse<TokenResponseDto> getToken(AuthUserRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getPhoneNumber(), dto.getPassword())
        );

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>");
        Optional<AuthUser> authUser = authRepository.findByPhoneNumber(principal.getUsername());

        Users users = userRepository
                .findByIdAndDeletedAtIsNull(authUser.get().getUsers().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with %d id is not found",
                        authUser.get().getUsers().getId())
                ));

        var accessToken = jwtUtil.createAccessToken(principal.getUsername(), users.getId(), authUser.get().getRole());
        var refreshToken = jwtUtil.createRefreshToken(principal.getUsername(), users.getId(), authUser.get().getRole());

        var response = TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return HttpApiResponse.<TokenResponseDto>builder()
                .content(response)
                .message("OK")
                .success(true)
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public HttpApiResponse<TokenResponseDto> refreshToken(String token) {
        boolean isValid = jwtUtil.validateRefreshToken(token);
        Long userId = null;
        String sub = null;
        AuthRole role = null;
        if (isValid){
            sub = jwtUtil.extractUsername(token);
            role = jwtUtil.getClaim("role", token, AuthRole.class);
            userId = jwtUtil.getClaim("userId", token, Long.class);
            var accessToken = jwtUtil.createAccessToken(sub, userId, role);
            var refreshToken = jwtUtil.createRefreshToken(sub, userId, role);

            var response = TokenResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            return HttpApiResponse.<TokenResponseDto>builder()
                    .content(response)
                    .message("OK")
                    .success(true)
                    .status(HttpStatus.OK)
                    .responseCode(HttpStatus.OK.value())
                    .build();
        }

        return HttpApiResponse.<TokenResponseDto>builder()
                .responseCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .message("Invalid token")
                .build();
    }

}
