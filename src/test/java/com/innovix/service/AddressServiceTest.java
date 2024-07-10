package com.innovix.service;

import com.innovix.entity.Address;
import com.innovix.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        address = new Address(1L, "Street Name", 123, "Unit 1", "12345-678", "City", "State", "Country", 1L);
    }

    @Test
    public void testListAll() {
        List<Address> addresses = Arrays.asList(address);
        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.listAll();
        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }

    @Test
    public void testSave() {
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address result = addressService.save(address);
        assertEquals(address, result);
    }

    @Test
    public void testFindById() {
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));

        Address result = addressService.findById(1L);
        assertEquals(address, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(addressRepository.findById(anyLong())).thenReturn(Optional.empty());

        Address result = addressService.findById(1L);
        assertNull(result);
    }

    @Test
    public void testFindByCity() {
        List<Address> addresses = Arrays.asList(address);
        when(addressRepository.findByCity(anyString())).thenReturn(addresses);

        List<Address> result = addressService.findByCity("City");
        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }

    @Test
    public void testFindByState() {
        List<Address> addresses = Arrays.asList(address);
        when(addressRepository.findByState(anyString())).thenReturn(addresses);

        List<Address> result = addressService.findByState("State");
        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }

    @Test
    public void testFindByCountry() {
        List<Address> addresses = Arrays.asList(address);
        when(addressRepository.findByCountry(anyString())).thenReturn(addresses);

        List<Address> result = addressService.findByCountry("Country");
        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }

    @Test
    public void testFindByPersonId() {
        List<Address> addresses = Arrays.asList(address);
        when(addressRepository.findByPersonId(anyLong())).thenReturn(addresses);

        List<Address> result = addressService.findByPersonId(1L);
        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }

    @Test
    public void testDelete() {
        doNothing().when(addressRepository).deleteById(anyLong());

        addressService.delete(1L);
        verify(addressRepository, times(1)).deleteById(1L);
    }
}
