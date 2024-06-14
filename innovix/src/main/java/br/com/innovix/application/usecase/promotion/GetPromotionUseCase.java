package br.com.innovix.application.usecase.promotion;

import br.com.innovix.domain.entity.Promotion;
import br.com.innovix.domain.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetPromotionUseCase {
    private final PromotionRepository promotionRepository;

    public GetPromotionUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Optional<Promotion> execute(Long promotionId) {
        return promotionRepository.findById(promotionId);
    }

    public List<Promotion> execute() {
        return promotionRepository.findAll();
    }
}
