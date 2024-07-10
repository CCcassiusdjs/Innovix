package com.innovix.mapper;

import com.innovix.dto.AddressDTO;
import com.innovix.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Address entity and AddressDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    /**
     * Converts an Address entity to an AddressDTO.
     *
     * @param address The Address entity to convert.
     * @return The converted AddressDTO.
     */
    @Mapping(source = "id", target = "addressId")
    AddressDTO toDto(Address address);

    /**
     * Converts an AddressDTO to an Address entity.
     *
     * @param addressDTO The AddressDTO to convert.
     * @return The converted Address entity.
     */
    @Mapping(source = "addressId", target = "id")
    Address toEntity(AddressDTO addressDTO);
}
