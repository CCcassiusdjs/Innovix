package com.innovix.usecase;

import com.innovix.entity.Product;
import com.innovix.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for managing operations related to {@link Product}.
 */
@Component
public class ProductUseCase {

    private final ProductService productService;

    /**
     * Constructor for dependency injection.
     *
     * @param productService the product service.
     */
    @Autowired
    public ProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Lists all products.
     *
     * @return a list of products.
     */
    public List<Product> listAllProducts() {
        return productService.listAll();
    }

    /**
     * Creates a new product.
     *
     * @param product the product to create.
     * @return the created product.
     */
    public Product createProduct(Product product) {
        return productService.save(product);
    }

    /**
     * Gets a product by ID.
     *
     * @param id the ID of the product.
     * @return the found product, or {@code null} if not found.
     */
    public Product getProductById(Long id) {
        return productService.findById(id);
    }

    /**
     * Lists products by name containing.
     *
     * @param name the name to search.
     * @return a list of products whose names contain the specified string.
     */
    public List<Product> listProductsByNameContaining(String name) {
        return productService.findByNameContaining(name);
    }

    /**
     * Lists products by category ID.
     *
     * @param categoryId the category ID.
     * @return a list of products in the specified category.
     */
    public List<Product> listProductsByCategoryId(Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    /**
     * Lists products by price range.
     *
     * @param minPrice the minimum price.
     * @param maxPrice the maximum price.
     * @return a list of products within the specified price range.
     */
    public List<Product> listProductsByPriceBetween(double minPrice, double maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Lists products by size.
     *
     * @param size the size.
     * @return a list of products with the specified size.
     */
    public List<Product> listProductsBySize(String size) {
        return productService.findBySize(size);
    }

    /**
     * Lists products by material.
     *
     * @param material the material.
     * @return a list of products with the specified material.
     */
    public List<Product> listProductsByMaterial(String material) {
        return productService.findByMaterial(material);
    }

    /**
     * Lists products by promotion ID.
     *
     * @param promotionId the promotion ID.
     * @return a list of products associated with the specified promotion.
     */
    public List<Product> listProductsByPromotionId(Long promotionId) {
        return productService.findByPromotionId(promotionId);
    }

    /**
     * Deletes a product by ID.
     *
     * @param id the ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        productService.delete(id);
    }
}
