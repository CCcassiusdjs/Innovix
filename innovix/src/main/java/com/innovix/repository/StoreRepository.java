package com.innovix.repository;

import com.innovix.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreName(String storeName);
    Store findByStoreCnpj(String storeCnpj);
    List<Store> findByCity(String city);
    List<Store> findByState(String state);
    List<Store> findByCountry(String country);
    List<Store> findByEmployeeId(Long employeeId);
}
