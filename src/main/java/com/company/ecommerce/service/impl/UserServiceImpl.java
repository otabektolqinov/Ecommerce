package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.AuthUser;
import com.company.ecommerce.domain.Users;
import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.UserRequestDto;
import com.company.ecommerce.dto.response.UserResponseDto;
import com.company.ecommerce.enums.AuthState;
import com.company.ecommerce.repository.AuthUserRepository;
import com.company.ecommerce.repository.UserRepository;
import com.company.ecommerce.service.UserService;
import com.company.ecommerce.service.mapper.UserMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthUserRepository authUserRepository;

    @Override
    @Transactional
    public HttpApiResponse<UserResponseDto> createUser(UserRequestDto dto) {
        AuthUser authUser = authUserRepository
                .findById(dto.getAuthUserId())
                .orElseThrow(()
                        -> new EntityNotFoundException(String.format("Auth User with %d id does not exist", dto.getAuthUserId())
                ));

        authUser.setAuthState(AuthState.ACTIVE);
        Users entity = userMapper.toEntity(dto);
        entity.setAuthUser(authUser);
        Users saved = userRepository.save(entity);
        authUser.setUsers(saved);

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

    @Override
    public HttpApiResponse<List<UserResponseDto>> getAll() {
        List<Users> all = userRepository.findAllByDeletedAtIsNull();

        return HttpApiResponse.<List<UserResponseDto>>builder()
                .content(all.stream().map(userMapper::toDto).toList())
                .status(HttpStatus.OK)
                .build();
    }
}
