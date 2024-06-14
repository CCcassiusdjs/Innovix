package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long orderId);
    List<Order> findAll();
    void deleteById(Long orderId);
}
