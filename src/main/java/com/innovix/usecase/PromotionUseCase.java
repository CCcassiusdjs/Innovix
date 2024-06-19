package com.innovix.usecase;

import com.innovix.entity.Promotion;
import com.innovix.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Use case for managing operations related to {@link Promotion}.
 */
@Component
public class PromotionUseCase {

    private final PromotionService promotionService;

    /**
     * Constructor for dependency injection.
     *
     * @param promotionService the promotion service.
     */
    @Autowired
    public PromotionUseCase(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    /**
     * Lists all promotions.
     *
     * @return a list of promotions.
     */
    public List<Promotion> listAllPromotions() {
        return promotionService.listAll();
    }

    /**
     * Creates a new promotion.
     *
     * @param promotion the promotion to create.
     * @return the created promotion.
     */
    public Promotion createPromotion(Promotion promotion) {
        return promotionService.save(promotion);
    }

    /**
     * Gets a promotion by ID.
     *
     * @param id the ID of the promotion.
     * @return the found promotion, or {@code null} if not found.
     */
    public Promotion getPromotionById(Long id) {
        if(promotionService.findById(id) != null){
            return promotionService.findById(id);
        }else{
            return null;
        }
    }

    /**
     * Lists promotions by season.
     *
     * @param season the season.
     * @return a list of promotions in the specified season.
     */
    public List<Promotion> listPromotionsBySeason(String season) {
        return promotionService.findBySeason(season);
    }

    /**
     * Lists promotions with init date before the specified date.
     *
     * @param date the date.
     * @return a list of promotions with init date before the specified date.
     */
    public List<Promotion> listPromotionsByInitLocalDateBefore(LocalDate date) {
        return promotionService.findByInitLocalDateBefore(date);
    }

    /**
     * Lists promotions with end date after the specified date.
     *
     * @param date the date.
     * @return a list of promotions with end date after the specified date.
     */
    public List<Promotion> listPromotionsByEndLocalDateAfter(LocalDate date) {
        return promotionService.findByEndLocalDateAfter(date);
    }

    /**
     * Lists promotions by employee ID.
     *
     * @param employeeId the employee ID.
     * @return a list of promotions associated with the specified employee.
     */
    public List<Promotion> listPromotionsByEmployeeId(Long employeeId) {
        return promotionService.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a promotion by ID.
     *
     * @param id the ID of the promotion to delete.
     */
    public void deletePromotion(Long id) {
        promotionService.delete(id);
    }
}
