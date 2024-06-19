package com.innovix.repository;

import com.innovix.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findBySize(String size);
    List<Product> findByMaterial(String material);
    List<Product> findByPromotionId(Long promotionId);
}
