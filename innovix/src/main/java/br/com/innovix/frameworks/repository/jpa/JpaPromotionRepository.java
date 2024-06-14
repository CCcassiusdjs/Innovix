package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.Promotion;
import br.com.innovix.domain.repository.PromotionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPromotionRepository extends JpaRepository<Promotion, Long>, PromotionRepository {
}
