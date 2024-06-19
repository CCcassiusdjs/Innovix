package com.innovix.usecase;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Person;
import com.innovix.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Use case for managing operations related to {@link PurchaseOrder}.
 */
@Component
public class OrderUseCase {

    private final OrderService orderService;

    /**
     * Constructor for dependency injection.
     *
     * @param orderService the order service.
     */
    @Autowired
    public OrderUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Lists all orders.
     *
     * @return a list of orders.
     */
    public List<PurchaseOrder> listAllOrders() {
        return orderService.listAll();
    }

    /**
     * Creates a new order.
     *
     * @param purchaseOrder the order to create.
     * @return the created order.
     */
    public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
        return orderService.save(purchaseOrder);
    }

    /**
     * Gets an order by ID.
     *
     * @param id the ID of the order.
     * @return the found order, or {@code null} if not found.
     */
    public PurchaseOrder getOrderById(Long id) {
        return orderService.findById(id);
    }

    /**
     * Lists orders by status.
     *
     * @param status the status of the orders.
     * @return a list of orders with the specified status.
     */
    public List<PurchaseOrder> listOrdersByStatus(String status) {
        return orderService.findByStatus(status);
    }

    /**
     * Lists orders by customer.
     *
     * @param customer the customer.
     * @return a list of orders for the specified customer.
     */
    public List<PurchaseOrder> listOrdersByCustomer(Person customer) {
        return orderService.findByCustomer(customer);
    }

    /**
     * Lists orders by date range.
     *
     * @param startLocalDate the start date.
     * @param endLocalDate the end date.
     * @return a list of orders within the specified date range.
     */
    public List<PurchaseOrder> listOrdersByLocalDateRange(LocalDate startLocalDate, LocalDate endLocalDate) {
        return orderService.findByLocalDateRange(startLocalDate, endLocalDate);
    }

    /**
     * Lists orders by product ID.
     *
     * @param productId the product ID.
     * @return a list of orders containing the specified product.
     */
    public List<PurchaseOrder> listOrdersByProductId(Long productId) {
        return orderService.findByProductId(productId);
    }

    /**
     * Deletes an order by ID.
     *
     * @param id the ID of the order to delete.
     */
    public void deleteOrder(Long id) {
        orderService.delete(id);
    }
}
