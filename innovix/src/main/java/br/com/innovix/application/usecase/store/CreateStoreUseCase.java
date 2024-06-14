package br.com.innovix.application.usecase.store;

import br.com.innovix.domain.entity.Store;
import br.com.innovix.domain.repository.StoreRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateStoreUseCase {
    private final StoreRepository storeRepository;

    public CreateStoreUseCase(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store execute(@Valid Store store) {
        return storeRepository.save(store);
    }
}
