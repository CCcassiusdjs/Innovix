package com.innovix.repository;

import com.innovix.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Promotion entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Promotion entities.
 * </p>
 */
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    /**
     * Finds promotions by season.
     *
     * @param season The season of the promotion.
     * @return A list of promotions for the specified season.
     */
    List<Promotion> findBySeason(String season);

    /**
     * Finds promotions with start dates before a specified date.
     *
     * @param date The date to compare against.
     * @return A list of promotions with start dates before the specified date.
     */
    List<Promotion> findByInitLocalDateBefore(LocalDate date);

    /**
     * Finds promotions with end dates after a specified date.
     *
     * @param date The date to compare against.
     * @return A list of promotions with end dates after the specified date.
     */
    List<Promotion> findByEndLocalDateAfter(LocalDate date);

    /**
     * Finds promotions by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of promotions associated with the specified employee ID.
     */
    List<Promotion> findByEmployeeId(Long employeeId);
}
