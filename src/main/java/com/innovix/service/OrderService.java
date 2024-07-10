package com.innovix.service;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Person;
import com.innovix.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing purchase orders.
 * <p>
 * This class provides methods for CRUD operations on PurchaseOrder entities.
 * </p>
 */
@Service
public class OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PromotionService promotionService;

    @Autowired
    public OrderService(PurchaseOrderRepository purchaseOrderRepository, PromotionService promotionService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.promotionService = promotionService;
    }

    /**
     * Lists all purchase orders.
     *
     * @return A list of all purchase orders.
     */
    public List<PurchaseOrder> listAll() {
        return purchaseOrderRepository.findAll();
    }

    /**
     * Saves a new purchase order or updates an existing one.
     *
     * @param purchaseOrder The purchase order to save.
     * @return The saved purchase order.
     */
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        promotionService.applyPromotion(purchaseOrder);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    /**
     * Finds a purchase order by its ID.
     *
     * @param id The ID of the purchase order.
     * @return The purchase order with the specified ID, or null if not found.
     */
    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    /**
     * Finds purchase orders by status.
     *
     * @param status The status of the order.
     * @return A list of purchase orders with the specified status.
     */
    public List<PurchaseOrder> findByStatus(String status) {
        return purchaseOrderRepository.findByOrderStatus(status);
    }

    /**
     * Finds purchase orders by customer.
     *
     * @param customer The customer associated with the order.
     * @return A list of purchase orders for the specified customer.
     */
    public List<PurchaseOrder> findByCustomer(Person customer) {
        return purchaseOrderRepository.findByCustomer(customer);
    }

    /**
     * Finds purchase orders within a specified date range.
     *
     * @param startLocalDate The start date of the range.
     * @param endLocalDate The end date of the range.
     * @return A list of purchase orders within the specified date range.
     */
    public List<PurchaseOrder> findByLocalDateRange(LocalDate startLocalDate, LocalDate endLocalDate) {
        return purchaseOrderRepository.findByOrderLocalDateBetween(startLocalDate, endLocalDate);
    }

    /**
     * Finds purchase orders by product ID.
     *
     * @param productId The ID of the product.
     * @return A list of purchase orders containing the specified product.
     */
    public List<PurchaseOrder> findByProductId(Long productId) {
        return purchaseOrderRepository.findByProductId(productId);
    }

    /**
     * Deletes a purchase order by its ID.
     *
     * @param id The ID of the purchase order to delete.
     */
    public void delete(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
