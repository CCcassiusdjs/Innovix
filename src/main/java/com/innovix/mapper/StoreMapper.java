package com.innovix.mapper;

import com.innovix.dto.StoreDTO;
import com.innovix.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link Store} and {@link StoreDTO}.
 */
@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    /**
     * Converts a {@link Store} entity to a {@link StoreDTO}.
     *
     * @param store the {@link Store} entity to convert.
     * @return the resulting {@link StoreDTO}.
     */
    @Mapping(source = "store.id", target = "storeId")
    @Mapping(source = "store.address.id", target = "addressId")
    @Mapping(source = "store.employee.id", target = "employeeId")
    StoreDTO toDto(Store store);

    /**
     * Converts a {@link StoreDTO} to a {@link Store} entity.
     *
     * @param storeDTO the {@link StoreDTO} to convert.
     * @return the resulting {@link Store} entity.
     */
    @Mapping(source = "storeDTO.storeId", target = "id")
    @Mapping(source = "storeDTO.addressId", target = "address.id")
    @Mapping(source = "storeDTO.employeeId", target = "employee.id")
    Store toEntity(StoreDTO storeDTO);
}
