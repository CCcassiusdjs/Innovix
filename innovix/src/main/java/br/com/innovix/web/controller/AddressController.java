package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.address.CreateAddressUseCase;
import br.com.innovix.application.usecase.address.DeleteAddressUseCase;
import br.com.innovix.application.usecase.address.GetAddressUseCase;
import br.com.innovix.application.usecase.address.UpdateAddressUseCase;
import br.com.innovix.domain.entity.Address;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private CreateAddressUseCase createAddressUseCase;

    @Autowired
    private GetAddressUseCase getAddressUseCase;

    @Autowired
    private UpdateAddressUseCase updateAddressUseCase;

    @Autowired
    private DeleteAddressUseCase deleteAddressUseCase;

    @PostMapping
    public Address createAddress(@RequestBody @Valid Address address) {
        return createAddressUseCase.execute(address);
    }

    @GetMapping("/{id}")
    public Optional<Address> getAddress(@PathVariable Long id) {
        return getAddressUseCase.execute(id);
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return getAddressUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<Address> updateAddress(@PathVariable Long id, @RequestBody @Valid Address address) {
        return updateAddressUseCase.execute(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        deleteAddressUseCase.execute(id);
    }
}
