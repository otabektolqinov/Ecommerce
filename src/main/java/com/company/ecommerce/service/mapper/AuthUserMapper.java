package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.AuthUser;
import com.company.ecommerce.dto.request.AuthUserRequestDto;
import com.company.ecommerce.dto.response.AuthUserResponseDto;
import com.company.ecommerce.enums.AuthRole;
import com.company.ecommerce.enums.AuthState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthState.class, AuthRole.class})
public interface AuthUserMapper {


    @Mapping(target = "users", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authState", ignore = true)
    @Mapping(target = "password", ignore = true)
    AuthUser toEntity(AuthUserRequestDto dto);

    @Mapping(target = "role", expression = "java(authUser.getRole().toString())")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "state", expression = "java(authUser.getAuthState().toString())")
    AuthUserResponseDto toDto(AuthUser authUser);

}
