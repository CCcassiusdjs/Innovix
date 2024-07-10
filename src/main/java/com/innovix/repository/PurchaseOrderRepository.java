package com.innovix.repository;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for PurchaseOrder entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for PurchaseOrder entities.
 * </p>
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    /**
     * Finds purchase orders by order status.
     *
     * @param orderStatus The status of the order.
     * @return A list of purchase orders with the specified status.
     */
    List<PurchaseOrder> findByOrderStatus(String orderStatus);

    /**
     * Finds purchase orders by customer.
     *
     * @param customer The customer associated with the order.
     * @return A list of purchase orders for the specified customer.
     */
    List<PurchaseOrder> findByCustomer(Person customer);

    /**
     * Finds purchase orders within a specified date range.
     *
     * @param startLocalDate The start date of the range.
     * @param endLocalDate The end date of the range.
     * @return A list of purchase orders within the specified date range.
     */
    List<PurchaseOrder> findByOrderLocalDateBetween(LocalDate startLocalDate, LocalDate endLocalDate);

    /**
     * Finds purchase orders by product ID.
     *
     * @param productId The ID of the product.
     * @return A list of purchase orders containing the specified product.
     */
    List<PurchaseOrder> findByProductId(Long productId);
}
