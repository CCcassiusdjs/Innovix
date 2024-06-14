package br.com.innovix.application.usecase.store;

import br.com.innovix.domain.entity.Store;
import br.com.innovix.domain.repository.StoreRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateStoreUseCase {
    private final StoreRepository storeRepository;

    public UpdateStoreUseCase(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Optional<Store> execute(Long storeId, @Valid Store store) {
        return storeRepository.findById(storeId).map(existingStore -> {
            existingStore.setName(store.getName());
            existingStore.setAddress(store.getAddress());
            existingStore.setPhone(store.getPhone());
            return storeRepository.save(existingStore);
        });
    }
}
