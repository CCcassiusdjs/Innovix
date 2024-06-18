package com.innovix.controller;

import com.innovix.dto.ProductDTO;
import com.innovix.mapper.ProductMapper;
import com.innovix.usecase.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    @Autowired
    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping
    public List<ProductDTO> listAll() {
        return productUseCase.listAllProducts().stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return ProductMapper.INSTANCE.toDto(
                productUseCase.createProduct(ProductMapper.INSTANCE.toEntity(productDTO))
        );
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return ProductMapper.INSTANCE.toDto(productUseCase.getProductById(id));
    }

    @GetMapping("/name/{name}")
    public List<ProductDTO> listByNameContaining(@PathVariable String name) {
        return productUseCase.listProductsByNameContaining(name).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> listByCategoryId(@PathVariable Long categoryId) {
        return productUseCase.listProductsByCategoryId(categoryId).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price-range")
    public List<ProductDTO> listByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productUseCase.listProductsByPriceBetween(minPrice, maxPrice).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/size/{size}")
    public List<ProductDTO> listBySize(@PathVariable String size) {
        return productUseCase.listProductsBySize(size).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/material/{material}")
    public List<ProductDTO> listByMaterial(@PathVariable String material) {
        return productUseCase.listProductsByMaterial(material).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/promotion/{promotionId}")
    public List<ProductDTO> listByPromotionId(@PathVariable Long promotionId) {
        return productUseCase.listProductsByPromotionId(promotionId).stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productUseCase.deleteProduct(id);
    }
}
