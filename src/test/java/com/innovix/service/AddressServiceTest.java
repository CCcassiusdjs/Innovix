package com.innovix.service;

import com.innovix.entity.Address;
import com.innovix.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;

    @BeforeEach
    void setUp() {
        address1 = new Address();
        address1.setId(1L);
        address1.setCity("City1");
        address1.setState("State1");
        address1.setCountry("Country1");
        address1.setPersonId(1L);

        address2 = new Address();
        address2.setId(2L);
        address2.setCity("City2");
        address2.setState("State2");
        address2.setCountry("Country2");
        address2.setPersonId(2L);
    }

    @Test
    void testListAll() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        List<Address> addresses = addressService.listAll();

        assertNotNull(addresses);
        assertEquals(2, addresses.size());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(addressRepository.save(address1)).thenReturn(address1);

        Address savedAddress = addressService.save(address1);

        assertNotNull(savedAddress);
        assertEquals(1L, savedAddress.getId());
        verify(addressRepository, times(1)).save(address1);
    }

    @Test
    void testFindById() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address1));

        Address foundAddress = addressService.findById(1L);

        assertNotNull(foundAddress);
        assertEquals(1L, foundAddress.getId());
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByCity() {
        when(addressRepository.findByCity("City1")).thenReturn(Arrays.asList(address1));

        List<Address> addresses = addressService.findByCity("City1");

        assertNotNull(addresses);
        assertEquals(1, addresses.size());
        verify(addressRepository, times(1)).findByCity("City1");
    }

    @Test
    void testDelete() {
        doNothing().when(addressRepository).deleteById(1L);

        addressService.delete(1L);

        verify(addressRepository, times(1)).deleteById(1L);
    }
}
