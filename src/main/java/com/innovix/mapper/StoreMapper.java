package com.innovix.mapper;

import com.innovix.dto.StoreDTO;
import com.innovix.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(source = "store.id", target = "storeId")
    @Mapping(source = "store.address.id", target = "addressId")
    @Mapping(source = "store.employee.id", target = "employeeId")
    StoreDTO toDto(Store store);

    @Mapping(source = "storeDTO.storeId", target = "id")
    @Mapping(source = "storeDTO.addressId", target = "address.id")
    @Mapping(source = "storeDTO.employeeId", target = "employee.id")
    Store toEntity(StoreDTO storeDTO);
}
