package br.com.innovix.application.usecase.promotion;

import br.com.innovix.domain.entity.Promotion;
import br.com.innovix.domain.repository.PromotionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreatePromotionUseCase {
    private final PromotionRepository promotionRepository;

    public CreatePromotionUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion execute(@Valid Promotion promotion) {
        return promotionRepository.save(promotion);
    }
}
