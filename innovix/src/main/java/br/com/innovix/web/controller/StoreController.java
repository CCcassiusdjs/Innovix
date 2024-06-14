package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.store.CreateStoreUseCase;
import br.com.innovix.application.usecase.store.DeleteStoreUseCase;
import br.com.innovix.application.usecase.store.GetStoreUseCase;
import br.com.innovix.application.usecase.store.UpdateStoreUseCase;
import br.com.innovix.domain.entity.Store;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private CreateStoreUseCase createStoreUseCase;

    @Autowired
    private GetStoreUseCase getStoreUseCase;

    @Autowired
    private UpdateStoreUseCase updateStoreUseCase;

    @Autowired
    private DeleteStoreUseCase deleteStoreUseCase;

    @PostMapping
    public Store createStore(@RequestBody @Valid Store store) {
        return createStoreUseCase.execute(store);
    }

    @GetMapping("/{id}")
    public Optional<Store> getStore(@PathVariable Long id) {
        return getStoreUseCase.execute(id);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return getStoreUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<Store> updateStore(@PathVariable Long id, @RequestBody @Valid Store store) {
        return updateStoreUseCase.execute(id, store);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        deleteStoreUseCase.execute(id);
    }
}
