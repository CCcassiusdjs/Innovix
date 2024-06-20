package com.innovix.mapper;

import com.innovix.dto.AddressDTO;
import com.innovix.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "address.id", target = "addressId")
    AddressDTO toDto(Address address);

    @Mapping(source = "addressDTO.addressId", target = "id")
    Address toEntity(AddressDTO addressDTO);
}
