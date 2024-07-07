package com.innovix.controller;

import com.innovix.dto.ProductDTO;
import com.innovix.mapper.ProductMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public ProductController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listAll() {
        return employeeUseCase.listAllProducts().stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return ProductMapper.INSTANCE.toDto(
                employeeUseCase.createProduct(ProductMapper.INSTANCE.toEntity(productDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public ProductDTO getById(@PathVariable Long id) {
        return ProductMapper.INSTANCE.toDto(employeeUseCase.getProductById(id));
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByNameContaining(@PathVariable String name) {
        return employeeUseCase.listProductsByNameContaining(name).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByCategoryId(@PathVariable Long categoryId) {
        return employeeUseCase.listProductsByCategoryId(categoryId).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price-range")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return employeeUseCase.listProductsByPriceBetween(minPrice, maxPrice).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/size/{size}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listBySize(@PathVariable String size) {
        return employeeUseCase.listProductsBySize(size).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/material/{material}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByMaterial(@PathVariable String material) {
        return employeeUseCase.listProductsByMaterial(material).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/promotion/{promotionId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<ProductDTO> listByPromotionId(@PathVariable Long promotionId) {
        return employeeUseCase.listProductsByPromotionId(promotionId).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteProduct(id);
    }
}
