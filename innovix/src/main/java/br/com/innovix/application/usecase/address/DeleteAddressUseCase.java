package br.com.innovix.application.usecase.address;

import br.com.innovix.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteAddressUseCase {
    private final AddressRepository addressRepository;

    public DeleteAddressUseCase(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void execute(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
