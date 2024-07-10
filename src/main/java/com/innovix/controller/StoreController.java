package com.innovix.controller;

import com.innovix.dto.StoreDTO;
import com.innovix.mapper.StoreMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing stores.
 */
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public StoreController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    /**
     * Lists all stores.
     *
     * @return A list of all stores.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listAll() {
        return employeeUseCase.listAllStores().stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new store.
     *
     * @param storeDTO The store data transfer object.
     * @return The saved store data transfer object.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public StoreDTO save(@RequestBody StoreDTO storeDTO) {
        return StoreMapper.INSTANCE.toDto(
                employeeUseCase.createStore(StoreMapper.INSTANCE.toEntity(storeDTO))
        );
    }

    /**
     * Gets a store by ID.
     *
     * @param id The ID of the store.
     * @return The store data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public StoreDTO getById(@PathVariable Long id) {
        return StoreMapper.INSTANCE.toDto(employeeUseCase.getStoreById(id));
    }

    /**
     * Gets a store by name.
     *
     * @param name The name of the store.
     * @return The store data transfer object.
     */
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public StoreDTO getByName(@PathVariable String name) {
        return StoreMapper.INSTANCE.toDto(employeeUseCase.getStoreByName(name));
    }

    /**
     * Gets a store by CNPJ.
     *
     * @param cnpj The CNPJ of the store.
     * @return The store data transfer object.
     */
    @GetMapping("/cnpj/{cnpj}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public StoreDTO getByCnpj(@PathVariable String cnpj) {
        return StoreMapper.INSTANCE.toDto(employeeUseCase.getStoreByCnpj(cnpj));
    }

    /**
     * Lists stores by city.
     *
     * @param city The city where the stores are located.
     * @return A list of stores in the specified city.
     */
    @GetMapping("/city/{city}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByCity(@PathVariable String city) {
        return employeeUseCase.listStoresByCity(city).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists stores by state.
     *
     * @param state The state where the stores are located.
     * @return A list of stores in the specified state.
     */
    @GetMapping("/state/{state}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByState(@PathVariable String state) {
        return employeeUseCase.listStoresByState(state).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists stores by country.
     *
     * @param country The country where the stores are located.
     * @return A list of stores in the specified country.
     */
    @GetMapping("/country/{country}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByCountry(@PathVariable String country) {
        return employeeUseCase.listStoresByCountry(country).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists stores by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of stores associated with the specified employee.
     */
    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<StoreDTO> listByEmployeeId(@PathVariable Long employeeId) {
        return employeeUseCase.listStoresByEmployeeId(employeeId).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a store by ID.
     *
     * @param id The ID of the store to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteStore(id);
    }
}
