package com.innovix.service;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Promotion;
import com.innovix.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Service to manage operations related to {@link Promotion}.
 */
@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param promotionRepository the promotion repository.
     */
    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    /**
     * Lists all promotions.
     *
     * @return a list of promotions.
     */
    public List<Promotion> listAll() {
        return promotionRepository.findAll();
    }

    /**
     * Saves a new promotion.
     *
     * @param promotion the promotion to save.
     * @return the saved promotion.
     */
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    /**
     * Finds a promotion by ID.
     *
     * @param id the ID of the promotion.
     * @return the found promotion, or {@code null} if not found.
     */
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    /**
     * Finds promotions by season.
     *
     * @param season the season.
     * @return a list of promotions in the specified season.
     */
    public List<Promotion> findBySeason(String season) {
        return promotionRepository.findBySeason(season);
    }

    /**
     * Finds promotions with init date before the specified date.
     *
     * @param date the date.
     * @return a list of promotions with init date before the specified date.
     */
    public List<Promotion> findByInitLocalDateBefore(LocalDate date) {
        return promotionRepository.findByInitLocalDateBefore(date);
    }

    /**
     * Finds promotions with end date after the specified date.
     *
     * @param date the date.
     * @return a list of promotions with end date after the specified date.
     */
    public List<Promotion> findByEndLocalDateAfter(LocalDate date) {
        return promotionRepository.findByEndLocalDateAfter(date);
    }

    /**
     * Finds promotions by employee ID.
     *
     * @param employeeId the employee ID.
     * @return a list of promotions associated with the specified employee.
     */
    public List<Promotion> findByEmployeeId(Long employeeId) {
        return promotionRepository.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a promotion by ID.
     *
     * @param id the ID of the promotion to delete.
     */
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    /**
     * Applies a promotion to an order.
     *
     * @param purchaseOrder the order to apply the promotion to.
     */
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
