package com.innovix.repository;

import com.innovix.entity.Order;
import com.innovix.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderStatus(String orderStatus);
    List<Order> findByCustomer(Person customer);
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);
    List<Order> findByProductId(Long productId);
}
