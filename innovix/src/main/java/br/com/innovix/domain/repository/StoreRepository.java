package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);
    Optional<Store> findById(Long storeId);
    List<Store> findAll();
    void deleteById(Long storeId);
}
