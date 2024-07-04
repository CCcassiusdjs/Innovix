package com.innovix.mapper;

import com.innovix.dto.AddressDTO;
import com.innovix.entity.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        Address address = new Address();
        address.setId(1L);
        address.setStreetName("123 Main St");
        address.setNumber(101);
        address.setUnit("Apt 1B");
        address.setZipCode("12345-678");
        address.setCity("Springfield");
        address.setState("IL");
        address.setCountry("USA");
        address.setPersonId(1L);

        // Act
        AddressDTO addressDTO = addressMapper.toDto(address);

        // Assert
        assertNotNull(addressDTO);
        assertEquals(1L, addressDTO.getAddressId());
        assertEquals("123 Main St", addressDTO.getStreetName());
        assertEquals(101, addressDTO.getNumber());
        assertEquals("Apt 1B", addressDTO.getUnit());
        assertEquals("12345-678", addressDTO.getZipCode());
        assertEquals("Springfield", addressDTO.getCity());
        assertEquals("IL", addressDTO.getState());
        assertEquals("USA", addressDTO.getCountry());
        assertEquals(1L, addressDTO.getPersonId());
    }

    @Test
    void toEntity() {
        // Arrange
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(1L);
        addressDTO.setStreetName("123 Main St");
        addressDTO.setNumber(101);
        addressDTO.setUnit("Apt 1B");
        addressDTO.setZipCode("12345-678");
        addressDTO.setCity("Springfield");
        addressDTO.setState("IL");
        addressDTO.setCountry("USA");
        addressDTO.setPersonId(1L);

        // Act
        Address address = addressMapper.toEntity(addressDTO);

        // Assert
        assertNotNull(address);
        assertEquals(1L, address.getId());
        assertEquals("123 Main St", address.getStreetName());
        assertEquals(101, address.getNumber());
        assertEquals("Apt 1B", address.getUnit());
        assertEquals("12345-678", address.getZipCode());
        assertEquals("Springfield", address.getCity());
        assertEquals("IL", address.getState());
        assertEquals("USA", address.getCountry());
        assertEquals(1L, address.getPersonId());
    }
}
