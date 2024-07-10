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
 * Service class for managing promotions.
 * <p>
 * This class provides methods for CRUD operations on Promotion entities.
 * </p>
 */
@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    /**
     * Lists all promotions.
     *
     * @return A list of all promotions.
     */
    public List<Promotion> listAll() {
        return promotionRepository.findAll();
    }

    /**
     * Saves a new promotion or updates an existing one.
     *
     * @param promotion The promotion to save.
     * @return The saved promotion.
     */
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    /**
     * Finds a promotion by its ID.
     *
     * @param id The ID of the promotion.
     * @return The promotion with the specified ID, or null if not found.
     */
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    /**
     * Finds promotions by season.
     *
     * @param season The season of the promotion.
     * @return A list of promotions for the specified season.
     */
    public List<Promotion> findBySeason(String season) {
        return promotionRepository.findBySeason(season);
    }

    /**
     * Finds promotions with start dates before a specified date.
     *
     * @param date The date to compare against.
     * @return A list of promotions with start dates before the specified date.
     */
    public List<Promotion> findByInitLocalDateBefore(LocalDate date) {
        return promotionRepository.findByInitLocalDateBefore(date);
    }

    /**
     * Finds promotions with end dates after a specified date.
     *
     * @param date The date to compare against.
     * @return A list of promotions with end dates after the specified date.
     */
    public List<Promotion> findByEndLocalDateAfter(LocalDate date) {
        return promotionRepository.findByEndLocalDateAfter(date);
    }

    /**
     * Finds promotions by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of promotions associated with the specified employee ID.
     */
    public List<Promotion> findByEmployeeId(Long employeeId) {
        return promotionRepository.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a promotion by its ID.
     *
     * @param id The ID of the promotion to delete.
     */
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    /**
     * Applies the promotion to the purchase order.
     *
     * @param purchaseOrder The purchase order to apply the promotion to.
     */
    public void applyPromotion(PurchaseOrder purchaseOrder) {
        Promotion promotion = purchaseOrder.getProduct().getPromotion();
        if (promotion != null && "buy2take3".equals(promotion.getDescription())) {
            applyBuy2Take3Promotion(purchaseOrder, promotion);
        }
    }

    /**
     * Applies the "buy 2, take 3" promotion to the purchase order.
     *
     * @param purchaseOrder The purchase order.
     * @param promotion     The promotion to apply.
     */
    void applyBuy2Take3Promotion(PurchaseOrder purchaseOrder, Promotion promotion) {
        int quantity = purchaseOrder.getProductQuantity();
        int freeItems = (quantity / promotion.getRequiredQuantity()) * promotion.getFreeQuantity();
        purchaseOrder.setFreeQuantity(freeItems);

        BigDecimal discount = BigDecimal.valueOf(freeItems).multiply(purchaseOrder.getProductPrice());
        BigDecimal newSubtotal = purchaseOrder.getProductSubtotal().subtract(discount);

        purchaseOrder.setProductSubtotal(newSubtotal);
    }
}
