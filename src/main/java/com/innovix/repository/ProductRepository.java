package com.innovix.repository;

import com.innovix.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Product entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Product entities.
 * </p>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds products by name containing a specified string.
     *
     * @param name The name to search for.
     * @return A list of products with names containing the specified string.
     */
    List<Product> findByNameContaining(String name);

    /**
     * Finds products by category ID.
     *
     * @param categoryId The ID of the category.
     * @return A list of products in the specified category.
     */
    List<Product> findByCategoryId(Long categoryId);

    /**
     * Finds products by price range.
     *
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products within the specified price range.
     */
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    /**
     * Finds products by size.
     *
     * @param size The size of the product.
     * @return A list of products with the specified size.
     */
    List<Product> findBySize(String size);

    /**
     * Finds products by material.
     *
     * @param material The material of the product.
     * @return A list of products with the specified material.
     */
    List<Product> findByMaterial(String material);

    /**
     * Finds products by promotion ID.
     *
     * @param promotionId The ID of the promotion.
     * @return A list of products associated with the specified promotion.
     */
    List<Product> findByPromotionId(Long promotionId);
}
