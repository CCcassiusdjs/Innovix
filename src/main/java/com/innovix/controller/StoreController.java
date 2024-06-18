package com.innovix.controller;

import com.innovix.dto.StoreDTO;
import com.innovix.mapper.StoreMapper;
import com.innovix.usecase.StoreUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreUseCase storeUseCase;

    @Autowired
    public StoreController(StoreUseCase storeUseCase) {
        this.storeUseCase = storeUseCase;
    }

    @GetMapping
    public List<StoreDTO> listAll() {
        return storeUseCase.listAllStores().stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public StoreDTO save(@RequestBody StoreDTO storeDTO) {
        return StoreMapper.INSTANCE.toDto(
                storeUseCase.createStore(StoreMapper.INSTANCE.toEntity(storeDTO))
        );
    }

    @GetMapping("/{id}")
    public StoreDTO getById(@PathVariable Long id) {
        return StoreMapper.INSTANCE.toDto(storeUseCase.getStoreById(id));
    }

    @GetMapping("/name/{name}")
    public StoreDTO getByName(@PathVariable String name) {
        return StoreMapper.INSTANCE.toDto(storeUseCase.getStoreByName(name));
    }

    @GetMapping("/cnpj/{cnpj}")
    public StoreDTO getByCnpj(@PathVariable String cnpj) {
        return StoreMapper.INSTANCE.toDto(storeUseCase.getStoreByCnpj(cnpj));
    }

    @GetMapping("/city/{city}")
    public List<StoreDTO> listByCity(@PathVariable String city) {
        return storeUseCase.listStoresByCity(city).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/state/{state}")
    public List<StoreDTO> listByState(@PathVariable String state) {
        return storeUseCase.listStoresByState(state).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/country/{country}")
    public List<StoreDTO> listByCountry(@PathVariable String country) {
        return storeUseCase.listStoresByCountry(country).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<StoreDTO> listByEmployeeId(@PathVariable Long employeeId) {
        return storeUseCase.listStoresByEmployeeId(employeeId).stream()
                .map(StoreMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        storeUseCase.deleteStore(id);
    }
}
