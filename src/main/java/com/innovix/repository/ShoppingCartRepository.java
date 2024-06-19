package com.innovix.repository;

import com.innovix.entity.ShoppingCart;
import com.innovix.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByCustomer(Person customer);
}
