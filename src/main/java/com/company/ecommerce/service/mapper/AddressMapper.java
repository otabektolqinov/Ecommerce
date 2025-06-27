package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Address;
import com.company.ecommerce.dto.request.AddressRequestDto;
import com.company.ecommerce.dto.response.AddressResponseDto;
import com.company.ecommerce.enums.AddressType;
import org.mapstruct.*;


@Mapper(componentModel = "spring", imports = {AddressType.class})
public interface AddressMapper {

    @Mapping(target = "addressType", expression = "java(AddressType.fromValue(dto.getAddressTypeIndex()))")
    Address toEntity(AddressRequestDto dto);

    @Mapping(target = "addressTypeIndex", expression = "java(address.getAddressType().getIndex())")
    AddressResponseDto toDto(Address address);

    @Mapping(
            target = "addressType",
            expression = "java(dto.getAddressTypeIndex() != null ? AddressType.fromValue(dto.getAddressTypeIndex()) : address.getAddressType())"
    )
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address updateAddress(@MappingTarget Address address, AddressRequestDto dto);

}
