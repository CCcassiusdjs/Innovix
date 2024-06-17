package com.innovix.service;

import com.innovix.entity.Order;
import com.innovix.entity.Person;
import com.innovix.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service to manage operations related to {@link Order}.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PromotionService promotionService;

    /**
     * Constructor for dependency injection.
     *
     * @param orderRepository the order repository.
     * @param promotionService the promotion service.
     */
    @Autowired
    public OrderService(OrderRepository orderRepository, PromotionService promotionService) {
        this.orderRepository = orderRepository;
        this.promotionService = promotionService;
    }

    /**
     * Lists all orders.
     *
     * @return a list of orders.
     */
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    /**
     * Saves a new order.
     *
     * @param order the order to save.
     * @return the saved order.
     */
    public Order save(Order order) {
        // Apply promotions before saving the order
        promotionService.applyPromotion(order);
        return orderRepository.save(order);
    }

    /**
     * Finds an order by ID.
     *
     * @param id the ID of the order.
     * @return the found order, or {@code null} if not found.
     */
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * Finds orders by status.
     *
     * @param status the status of the orders.
     * @return a list of orders with the specified status.
     */
    public List<Order> findByStatus(String status) {
        return orderRepository.findByOrderStatus(status);
    }

    /**
     * Finds orders by customer.
     *
     * @param customer the customer.
     * @return a list of orders for the specified customer.
     */
    public List<Order> findByCustomer(Person customer) {
        return orderRepository.findByCustomer(customer);
    }

    /**
     * Finds orders by date range.
     *
     * @param startDate the start date.
     * @param endDate the end date.
     * @return a list of orders within the specified date range.
     */
    public List<Order> findByDateRange(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    /**
     * Finds orders by product ID.
     *
     * @param productId the product ID.
     * @return a list of orders containing the specified product.
     */
    public List<Order> findByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }

    /**
     * Deletes an order by ID.
     *
     * @param id the ID of the order to delete.
     */
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
