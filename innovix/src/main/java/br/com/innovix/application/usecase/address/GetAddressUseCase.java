package br.com.innovix.application.usecase.address;

import br.com.innovix.domain.entity.Address;
import br.com.innovix.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetAddressUseCase {
    private final AddressRepository addressRepository;

    public GetAddressUseCase(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> execute(Long addressId) {
        return addressRepository.findById(addressId);
    }

    public List<Address> execute() {
        return addressRepository.findAll();
    }
}
