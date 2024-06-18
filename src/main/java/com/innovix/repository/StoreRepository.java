package com.innovix.repository;

import com.innovix.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreName(String storeName);
    Store findByStoreCnpj(String storeCnpj);
    List<Store> findByAddressCity(String city);
    List<Store> findByAddressState(String state);
    List<Store> findByAddressCountry(String country);
    List<Store> findByEmployeeId(Long employeeId);
}
