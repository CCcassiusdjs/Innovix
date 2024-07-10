package com.innovix.controller;

import com.innovix.dto.ProductDTO;
import com.innovix.mapper.ProductMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public ProductController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    /**
     * Lists all products.
     *
     * @return A list of all products.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listAll() {
        return employeeUseCase.listAllProducts().stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new product.
     *
     * @param productDTO  The product data transfer object.
     * @return The saved product data transfer object.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return ProductMapper.INSTANCE.toDto(
                employeeUseCase.createProduct(ProductMapper.INSTANCE.toEntity(productDTO))
        );
    }

    /**
     * Gets a product by ID.
     *
     * @param id  The ID of the product.
     * @return The product data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public ProductDTO getById(@PathVariable Long id) {
        return ProductMapper.INSTANCE.toDto(employeeUseCase.getProductById(id));
    }

    /**
     * Lists products by name containing the specified string.
     *
     * @param name  The string to search for in product names.
     * @return A list of products containing the specified string in their names.
     */
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByNameContaining(@PathVariable String name) {
        return employeeUseCase.listProductsByNameContaining(name).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists products by category ID.
     *
     * @param categoryId  The ID of the category.
     * @return A list of products in the specified category.
     */
    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByCategoryId(@PathVariable Long categoryId) {
        return employeeUseCase.listProductsByCategoryId(categoryId).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists products by price range.
     *
     * @param minPrice  The minimum price.
     * @param maxPrice  The maximum price.
     * @return A list of products within the specified price range.
     */
    @GetMapping("/price-range")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return employeeUseCase.listProductsByPriceBetween(minPrice, maxPrice).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists products by size.
     *
     * @param size  The size of the products.
     * @return A list of products with the specified size.
     */
    @GetMapping("/size/{size}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listBySize(@PathVariable String size) {
        return employeeUseCase.listProductsBySize(size).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists products by material.
     *
     * @param material  The material of the products.
     * @return A list of products made from the specified material.
     */
    @GetMapping("/material/{material}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByMaterial(@PathVariable String material) {
        return employeeUseCase.listProductsByMaterial(material).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists products by promotion ID.
     *
     * @param promotionId  The ID of the promotion.
     * @return A list of products associated with the specified promotion.
     */
    @GetMapping("/promotion/{promotionId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByPromotionId(@PathVariable Long promotionId) {
        return employeeUseCase.listProductsByPromotionId(promotionId).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a product by ID.
     *
     * @param id  The ID of the product to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteProduct(id);
    }
}
