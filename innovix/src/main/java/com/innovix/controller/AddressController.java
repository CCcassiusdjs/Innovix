package com.innovix.controller;

import com.innovix.dto.AddressDTO;
import com.innovix.mapper.AddressMapper;
import com.innovix.usecase.AddressUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressUseCase addressUseCase;

    @Autowired
    public AddressController(AddressUseCase addressUseCase) {
        this.addressUseCase = addressUseCase;
    }

    @GetMapping
    public List<AddressDTO> listAll() {
        return addressUseCase.listAllAddresses().stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public AddressDTO save(@RequestBody AddressDTO addressDTO) {
        return AddressMapper.INSTANCE.toDto(
                addressUseCase.createAddress(AddressMapper.INSTANCE.toEntity(addressDTO))
        );
    }

    @GetMapping("/{id}")
    public AddressDTO getById(@PathVariable Long id) {
        return AddressMapper.INSTANCE.toDto(addressUseCase.getAddressById(id));
    }

    @GetMapping("/city/{city}")
    public List<AddressDTO> listByCity(@PathVariable String city) {
        return addressUseCase.listAddressesByCity(city).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/state/{state}")
    public List<AddressDTO> listByState(@PathVariable String state) {
        return addressUseCase.listAddressesByState(state).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/country/{country}")
    public List<AddressDTO> listByCountry(@PathVariable String country) {
        return addressUseCase.listAddressesByCountry(country).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/person/{personId}")
    public List<AddressDTO> listByPersonId(@PathVariable Long personId) {
        return addressUseCase.listAddressesByPersonId(personId).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressUseCase.deleteAddress(id);
    }
}
