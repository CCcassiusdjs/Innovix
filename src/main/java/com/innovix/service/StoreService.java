package com.innovix.service;

import com.innovix.entity.Store;
import com.innovix.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> listAll() {
        return storeRepository.findAll();
    }

    public Store save(Store store) {
        return storeRepository.save(store);
    }


    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }


    public Store findByName(String name) {
        return storeRepository.findByStoreName(name);
    }

    public Store findByCnpj(String cnpj) {
        return storeRepository.findByStoreCnpj(cnpj);
    }


    public List<Store> findByCity(String city) {
        return storeRepository.findByAddressCity(city);
    }


    public List<Store> findByState(String state) {
        return storeRepository.findByAddressState(state);
    }


    public List<Store> findByCountry(String country) {
        return storeRepository.findByAddressCountry(country);
    }


    public List<Store> findByEmployeeId(Long employeeId) {
        return storeRepository.findByEmployeeId(employeeId);
    }

    public void delete(Long id) {
        storeRepository.deleteById(id);
    }
}
