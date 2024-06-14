package br.com.innovix.application.usecase.address;

import br.com.innovix.domain.entity.Address;
import br.com.innovix.domain.repository.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateAddressUseCase {
    private final AddressRepository addressRepository;

    public UpdateAddressUseCase(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> execute(Long addressId, @Valid Address address) {
        return addressRepository.findById(addressId).map(existingAddress -> {
            existingAddress.setStreet(address.getStreet());
            existingAddress.setCity(address.getCity());
            existingAddress.setState(address.getState());
            existingAddress.setZipCode(address.getZipCode());
            return addressRepository.save(existingAddress);
        });
    }
}
