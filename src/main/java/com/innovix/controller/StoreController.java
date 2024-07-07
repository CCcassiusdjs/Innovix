package com.innovix.controller;

import com.innovix.dto.StoreDTO;
import com.innovix.mapper.StoreMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public StoreController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listAll() {
        return employeeUseCase.listAllStores().stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public StoreDTO save(@RequestBody StoreDTO storeDTO) {
        return StoreMapper.INSTANCE.toDto(
                employeeUseCase.createStore(StoreMapper.INSTANCE.toEntity(storeDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public StoreDTO getById(@PathVariable Long id) {
        return StoreMapper.INSTANCE.toDto(employeeUseCase.getStoreById(id));
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public StoreDTO getByName(@PathVariable String name) {
        return StoreMapper.INSTANCE.toDto(employeeUseCase.getStoreByName(name));
    }

    @GetMapping("/cnpj/{cnpj}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public StoreDTO getByCnpj(@PathVariable String cnpj) {
        return StoreMapper.INSTANCE.toDto(employeeUseCase.getStoreByCnpj(cnpj));
    }

    @GetMapping("/city/{city}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByCity(@PathVariable String city) {
        return employeeUseCase.listStoresByCity(city).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/state/{state}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByState(@PathVariable String state) {
        return employeeUseCase.listStoresByState(state).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/country/{country}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByCountry(@PathVariable String country) {
        return employeeUseCase.listStoresByCountry(country).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByEmployeeId(@PathVariable Long employeeId) {
        return employeeUseCase.listStoresByEmployeeId(employeeId).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteStore(id);
    }
}
