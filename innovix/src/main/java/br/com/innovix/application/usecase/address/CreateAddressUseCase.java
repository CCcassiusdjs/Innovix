package br.com.innovix.application.usecase.address;

import br.com.innovix.domain.entity.Address;
import br.com.innovix.domain.repository.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateAddressUseCase {
    private final AddressRepository addressRepository;

    public CreateAddressUseCase(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address execute(@Valid Address address) {
        return addressRepository.save(address);
    }
}
