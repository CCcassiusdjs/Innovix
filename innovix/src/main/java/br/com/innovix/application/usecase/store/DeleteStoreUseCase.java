package br.com.innovix.application.usecase.store;

import br.com.innovix.domain.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteStoreUseCase {
    private final StoreRepository storeRepository;

    public DeleteStoreUseCase(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void execute(Long storeId) {
        storeRepository.deleteById(storeId);
    }
}
