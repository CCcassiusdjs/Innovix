package com.innovix.service;

import com.innovix.entity.Product;
import com.innovix.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findBySize(String size) {
        return productRepository.findBySize(size);
    }

    public List<Product> findByMaterial(String material) {
        return productRepository.findByMaterial(material);
    }

    public List<Product> findByPromotionId(Long promotionId) {
        return productRepository.findByPromotionId(promotionId);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
