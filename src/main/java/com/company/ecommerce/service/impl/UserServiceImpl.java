package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Users;
import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.UserRequestDto;
import com.company.ecommerce.dto.response.UserResponseDto;
import com.company.ecommerce.repository.UserRepository;
import com.company.ecommerce.service.UserService;
import com.company.ecommerce.service.mapper.UserMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public HttpApiResponse<UserResponseDto> createUser(UserRequestDto dto) {
        Users entity = userMapper.toEntity(dto);
        Users saved = userRepository.save(entity);
        return HttpApiResponse.<UserResponseDto>builder()
                .content(userMapper.toDto(saved))
                .message("Successfully saved users")
                .status(HttpStatus.CREATED)
                .responseCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
    }

    @Override
    public HttpApiResponse<UserResponseDto> getUsersById(Long id) {
        Optional<Users> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            return ResponseUtils.buildNotFoundResponse("Users", id);
        }

        return HttpApiResponse.<UserResponseDto>builder()
                .message("OK")
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .content(userMapper.toDto(optional.get()))
                .success(true)
                .build();
    }

    @Override
    public HttpApiResponse<UserResponseDto> updateUserById(Long id, UserRequestDto dto) {
        Optional<Users> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            return ResponseUtils.buildNotFoundResponse("Users", id);
        }

        Users updatedUser = userMapper.updateUser(optional.get(), dto);
        Users users = userRepository.save(updatedUser);

        return HttpApiResponse.<UserResponseDto>builder()
                .content(userMapper.toDto(users))
                .message("Successfully updated users")
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteUserById(Long id) {
        Optional<Users> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            return ResponseUtils.buildNotFoundResponse("Users", id);
        }

        Users users = optional.get();
        users.setDeletedAt(LocalDateTime.now());
        userRepository.save(users);

        return HttpApiResponse.<String>builder()
                .content("User deleted successfully")
                .message("Successfully deleted users")
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }
}
