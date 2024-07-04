package com.innovix.mapper;

import com.innovix.dto.StoreDTO;
import com.innovix.entity.Person;
import com.innovix.entity.Store;
import com.innovix.entity.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreMapperTest {

    private final StoreMapper storeMapper = StoreMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        Store store = new Store();
        store.setId(1L);
        Address address = new Address();
        address.setId(2L);
        store.setAddress(address);
        Person employee = new Person();
        employee.setId(3L);
        store.setEmployee(employee);

        // Act
        StoreDTO storeDTO = storeMapper.toDto(store);

        // Assert
        assertNotNull(storeDTO);
        assertEquals(1L, storeDTO.getStoreId());
        assertEquals(2L, storeDTO.getAddressId());
        assertEquals(3L, storeDTO.getEmployeeId());
    }

    @Test
    void toEntity() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(1L);
        storeDTO.setAddressId(2L);
        storeDTO.setEmployeeId(3L);

        // Act
        Store store = storeMapper.toEntity(storeDTO);

        // Assert
        assertNotNull(store);
        assertEquals(1L, store.getId());
        assertNotNull(store.getAddress());
        assertEquals(2L, store.getAddress().getId());
        assertNotNull(store.getEmployee());
        assertEquals(3L, store.getEmployee().getId());
    }
}
