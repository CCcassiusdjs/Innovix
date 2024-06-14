package br.com.innovix.application.usecase.promotion;

import br.com.innovix.domain.repository.PromotionRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePromotionUseCase {
    private final PromotionRepository promotionRepository;

    public DeletePromotionUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public void execute(Long promotionId) {
        promotionRepository.deleteById(promotionId);
    }
}
