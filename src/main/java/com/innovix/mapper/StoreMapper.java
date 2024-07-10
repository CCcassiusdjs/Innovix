package com.innovix.mapper;

import com.innovix.dto.StoreDTO;
import com.innovix.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Store entity and StoreDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    /**
     * Converts a Store entity to a StoreDTO.
     *
     * @param store The Store entity to convert.
     * @return The converted StoreDTO.
     */
    @Mapping(source = "id", target = "storeId")
    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "employee.id", target = "employeeId")
    StoreDTO toDto(Store store);

    /**
     * Converts a StoreDTO to a Store entity.
     *
     * @param storeDTO The StoreDTO to convert.
     * @return The converted Store entity.
     */
    @Mapping(source = "storeId", target = "id")
    @Mapping(source = "addressId", target = "address.id")
    @Mapping(source = "employeeId", target = "employee.id")
    Store toEntity(StoreDTO storeDTO);
}
