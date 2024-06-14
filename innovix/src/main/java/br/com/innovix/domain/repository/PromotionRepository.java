package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.Promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository {
    Promotion save(Promotion promotion);
    Optional<Promotion> findById(Long promotionId);
    List<Promotion> findAll();
    void deleteById(Long promotionId);
}
