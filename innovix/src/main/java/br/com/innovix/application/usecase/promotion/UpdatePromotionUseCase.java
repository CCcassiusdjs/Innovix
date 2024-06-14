package br.com.innovix.application.usecase.promotion;

import br.com.innovix.domain.entity.Promotion;
import br.com.innovix.domain.repository.PromotionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePromotionUseCase {
    private final PromotionRepository promotionRepository;

    public UpdatePromotionUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Optional<Promotion> execute(Long promotionId, @Valid Promotion promotion) {
        return promotionRepository.findById(promotionId).map(existingPromotion -> {
            existingPromotion.setName(promotion.getName());
            existingPromotion.setStartDate(promotion.getStartDate());
            existingPromotion.setEndDate(promotion.getEndDate());
            existingPromotion.setProducts(promotion.getProducts());
            return promotionRepository.save(existingPromotion);
        });
    }
}
