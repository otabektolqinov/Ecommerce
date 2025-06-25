package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Users;
import com.company.ecommerce.dto.request.UserRequestDto;
import com.company.ecommerce.dto.response.UserResponseDto;
import com.company.ecommerce.enums.Gender;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = { Gender.class })
public interface UserMapper {

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "gender", expression = "java(Gender.fromValue(dto.getGenderIndex()))")
    @Mapping(target = "cartItemList", ignore = true)
    Users toEntity(UserRequestDto dto);

    @Mapping(target = "genderIndex", expression = "java(users.getGender().getIndex())")
    UserResponseDto toDto(Users users);

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "cartItemList", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users updateUser(@MappingTarget Users users, UserRequestDto dto);

}
