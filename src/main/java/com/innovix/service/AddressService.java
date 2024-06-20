package com.innovix.service;

import com.innovix.dto.AddressDTO;
import com.innovix.entity.Address;
import com.innovix.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> listAll() {
        return addressRepository.findAll();
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    public List<Address> findByState(String state) {
        return addressRepository.findByState(state);
    }

    public List<Address> findByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    public List<Address> findByPersonId(Long personId) {
        return addressRepository.findByPersonId(personId);
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
