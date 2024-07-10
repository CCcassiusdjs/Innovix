package com.innovix.service;

import com.innovix.entity.Product;
import com.innovix.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing products.
 * <p>
 * This class provides methods for CRUD operations on Product entities.
 * </p>
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Lists all products.
     *
     * @return A list of all products.
     */
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    /**
     * Saves a new product or updates an existing one.
     *
     * @param product The product to save.
     * @return The saved product.
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product.
     * @return The product with the specified ID, or null if not found.
     */
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Finds products by name containing a specified string.
     *
     * @param name The name to search for.
     * @return A list of products with names containing the specified string.
     */
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    /**
     * Finds products by category ID.
     *
     * @param categoryId The ID of the category.
     * @return A list of products in the specified category.
     */
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    /**
     * Finds products by price range.
     *
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products within the specified price range.
     */
    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Finds products by size.
     *
     * @param size The size of the product.
     * @return A list of products with the specified size.
     */
    public List<Product> findBySize(String size) {
        return productRepository.findBySize(size);
    }

    /**
     * Finds products by material.
     *
     * @param material The material of the product.
     * @return A list of products with the specified material.
     */
    public List<Product> findByMaterial(String material) {
        return productRepository.findByMaterial(material);
    }

    /**
     * Finds products by promotion ID.
     *
     * @param promotionId The ID of the promotion.
     * @return A list of products associated with the specified promotion.
     */
    public List<Product> findByPromotionId(Long promotionId) {
        return productRepository.findByPromotionId(promotionId);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
