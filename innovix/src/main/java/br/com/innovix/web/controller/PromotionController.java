package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.promotion.CreatePromotionUseCase;
import br.com.innovix.application.usecase.promotion.DeletePromotionUseCase;
import br.com.innovix.application.usecase.promotion.GetPromotionUseCase;
import br.com.innovix.application.usecase.promotion.UpdatePromotionUseCase;
import br.com.innovix.domain.entity.Promotion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promotions")
public class PromotionController {
    @Autowired
    private CreatePromotionUseCase createPromotionUseCase;

    @Autowired
    private GetPromotionUseCase getPromotionUseCase;

    @Autowired
    private UpdatePromotionUseCase updatePromotionUseCase;

    @Autowired
    private DeletePromotionUseCase deletePromotionUseCase;

    @PostMapping
    public Promotion createPromotion(@RequestBody @Valid Promotion promotion) {
        return createPromotionUseCase.execute(promotion);
    }

    @GetMapping("/{id}")
    public Optional<Promotion> getPromotion(@PathVariable Long id) {
        return getPromotionUseCase.execute(id);
    }

    @GetMapping
    public List<Promotion> getAllPromotions() {
        return getPromotionUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<Promotion> updatePromotion(@PathVariable Long id, @RequestBody @Valid Promotion promotion) {
        return updatePromotionUseCase.execute(id, promotion);
    }

    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable Long id) {
        deletePromotionUseCase.execute(id);
    }
}
