package br.com.innovix.application.usecase.store;

import br.com.innovix.domain.entity.Store;
import br.com.innovix.domain.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetStoreUseCase {
    private final StoreRepository storeRepository;

    public GetStoreUseCase(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Optional<Store> execute(Long storeId) {
        return storeRepository.findById(storeId);
    }

    public List<Store> execute() {
        return storeRepository.findAll();
    }
}
