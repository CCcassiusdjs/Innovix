package com.innovix.service;

import com.innovix.entity.Product;
import com.innovix.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage operations related to {@link Product}.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param productRepository the product repository.
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Lists all products.
     *
     * @return a list of products.
     */
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    /**
     * Saves a new product.
     *
     * @param product the product to save.
     * @return the saved product.
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * Finds a product by ID.
     *
     * @param id the ID of the product.
     * @return the found product, or {@code null} if not found.
     */
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Finds products by name containing.
     *
     * @param name the name to search.
     * @return a list of products whose names contain the specified string.
     */
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    /**
     * Finds products by category ID.
     *
     * @param categoryId the category ID.
     * @return a list of products in the specified category.
     */
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    /**
     * Finds products by price range.
     *
     * @param minPrice the minimum price.
     * @param maxPrice the maximum price.
     * @return a list of products within the specified price range.
     */
    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Finds products by size.
     *
     * @param size the size.
     * @return a list of products with the specified size.
     */
    public List<Product> findBySize(String size) {
        return productRepository.findBySize(size);
    }

    /**
     * Finds products by material.
     *
     * @param material the material.
     * @return a list of products with the specified material.
     */
    public List<Product> findByMaterial(String material) {
        return productRepository.findByMaterial(material);
    }

    /**
     * Finds products by promotion ID.
     *
     * @param promotionId the promotion ID.
     * @return a list of products associated with the specified promotion.
     */
    public List<Product> findByPromotionId(Long promotionId) {
        return productRepository.findByPromotionId(promotionId);
    }

    /**
     * Deletes a product by ID.
     *
     * @param id the ID of the product to delete.
     */
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
