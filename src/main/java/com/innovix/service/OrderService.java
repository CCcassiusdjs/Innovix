package com.innovix.service;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Person;
import com.innovix.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service to manage operations related to {@link PurchaseOrder}.
 */
@Service
public class OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PromotionService promotionService;

    /**
     * Constructor for dependency injection.
     *
     * @param purchaseOrderRepository the order repository.
     * @param promotionService the promotion service.
     */
    @Autowired
    public OrderService(PurchaseOrderRepository purchaseOrderRepository, PromotionService promotionService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.promotionService = promotionService;
    }

    /**
     * Lists all orders.
     *
     * @return a list of orders.
     */
    public List<PurchaseOrder> listAll() {
        return purchaseOrderRepository.findAll();
    }

    /**
     * Saves a new order.
     *
     * @param purchaseOrder the order to save.
     * @return the saved order.
     */
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        // Apply promotions before saving the order
        promotionService.applyPromotion(purchaseOrder);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    /**
     * Finds an order by ID.
     *
     * @param id the ID of the order.
     * @return the found order, or {@code null} if not found.
     */
    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    /**
     * Finds orders by status.
     *
     * @param status the status of the orders.
     * @return a list of orders with the specified status.
     */
    public List<PurchaseOrder> findByStatus(String status) {
        return purchaseOrderRepository.findByOrderStatus(status);
    }

    /**
     * Finds orders by customer.
     *
     * @param customer the customer.
     * @return a list of orders for the specified customer.
     */
    public List<PurchaseOrder> findByCustomer(Person customer) {
        return purchaseOrderRepository.findByCustomer(customer);
    }

    /**
     * Finds orders by date range.
     *
     * @param startDate the start date.
     * @param endDate the end date.
     * @return a list of orders within the specified date range.
     */
    public List<PurchaseOrder> findByDateRange(Date startDate, Date endDate) {
        return purchaseOrderRepository.findByOrderDateBetween(startDate, endDate);
    }

    /**
     * Finds orders by product ID.
     *
     * @param productId the product ID.
     * @return a list of orders containing the specified product.
     */
    public List<PurchaseOrder> findByProductId(Long productId) {
        return purchaseOrderRepository.findByProductId(productId);
    }

    /**
     * Deletes an order by ID.
     *
     * @param id the ID of the order to delete.
     */
    public void delete(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
