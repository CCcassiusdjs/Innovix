package com.innovix.controller;

import com.innovix.dto.AddressDTO;
import com.innovix.mapper.AddressMapper;
import com.innovix.usecase.CustomerUseCase;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final CustomerUseCase customerUseCase;
    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public AddressController(CustomerUseCase customerUseCase, EmployeeUseCase employeeUseCase) {
        this.customerUseCase = customerUseCase;
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<AddressDTO> listAll() {
        return employeeUseCase.listAllAddresses().stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public AddressDTO save(@RequestBody AddressDTO addressDTO) {
        return AddressMapper.INSTANCE.toDto(
                employeeUseCase.createAddress(AddressMapper.INSTANCE.toEntity(addressDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public AddressDTO getById(@PathVariable Long id) {
        return AddressMapper.INSTANCE.toDto(employeeUseCase.getAddressById(id));
    }

    @GetMapping("/city/{city}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<AddressDTO> listByCity(@PathVariable String city) {
        return employeeUseCase.listAddressesByCity(city).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/state/{state}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<AddressDTO> listByState(@PathVariable String state) {
        return employeeUseCase.listAddressesByState(state).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/country/{country}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<AddressDTO> listByCountry(@PathVariable String country) {
        return employeeUseCase.listAddressesByCountry(country).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/person/{personId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<AddressDTO> listByPersonId(@PathVariable Long personId) {
        return employeeUseCase.listAddressesByPersonId(personId).stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteAddress(id);
    }
}
