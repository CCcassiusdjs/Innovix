package com.innovix.service;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Promotion;
import com.innovix.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> listAll() {
        return promotionRepository.findAll();
    }

    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    public List<Promotion> findBySeason(String season) {
        return promotionRepository.findBySeason(season);
    }

    public List<Promotion> findByInitLocalDateBefore(LocalDate date) {
        return promotionRepository.findByInitLocalDateBefore(date);
    }

    public List<Promotion> findByEndLocalDateAfter(LocalDate date) {
        return promotionRepository.findByEndLocalDateAfter(date);
    }

    public List<Promotion> findByEmployeeId(Long employeeId) {
        return promotionRepository.findByEmployeeId(employeeId);
    }

    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    public void applyPromotion(PurchaseOrder purchaseOrder) {
        Promotion promotion = purchaseOrder.getProduct().getPromotion();
        if (promotion != null && "buy2take3".equals(promotion.getDescription())) {
            applyBuy2Take3Promotion(purchaseOrder, promotion);
        }
    }

    private void applyBuy2Take3Promotion(PurchaseOrder purchaseOrder, Promotion promotion) {
        int quantity = purchaseOrder.getProductQuantity();
        int freeItems = (quantity / promotion.getRequiredQuantity()) * promotion.getFreeQuantity();
        purchaseOrder.setFreeQuantity(freeItems);

        BigDecimal discount = BigDecimal.valueOf(freeItems).multiply(purchaseOrder.getProductPrice());
        BigDecimal newSubtotal = purchaseOrder.getProductSubtotal().subtract(discount);

        purchaseOrder.setProductSubtotal(newSubtotal);
    }
}
