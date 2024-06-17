package com.innovix.mapper;

import com.innovix.dto.AddressDTO;
import com.innovix.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link Address} and {@link AddressDTO}.
 */
@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    /**
     * Converts an {@link Address} entity to an {@link AddressDTO}.
     *
     * @param address the {@link Address} entity to convert.
     * @return the resulting {@link AddressDTO}.
     */
    @Mapping(source = "address.addressId", target = "addressId")
    AddressDTO toDto(Address address);

    /**
     * Converts an {@link AddressDTO} to an {@link Address} entity.
     *
     * @param addressDTO the {@link AddressDTO} to convert.
     * @return the resulting {@link Address} entity.
     */
    @Mapping(source = "addressDTO.addressId", target = "addressId")
    Address toEntity(AddressDTO addressDTO);
}
