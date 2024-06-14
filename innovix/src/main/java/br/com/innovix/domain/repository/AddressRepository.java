package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    Address save(Address address);
    Optional<Address> findById(Long addressId);
    List<Address> findAll();
    void deleteById(Long addressId);
}
