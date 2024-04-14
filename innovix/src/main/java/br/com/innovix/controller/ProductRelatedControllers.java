package br.com.innovix.controller;

import br.com.innovix.dto.ProductRelatedDTOs;
import br.com.innovix.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public class ProductRelatedControllers {

    @RestController
    @RequestMapping("/inventory")
    public static class InventoryController {

        private final ProductServices.InventoryService inventoryService;

        @Autowired
        public InventoryController(ProductServices.InventoryService inventoryService) {
            this.inventoryService = inventoryService;
        }

        
        @PutMapping("/{id}")
        public ProductRelatedDTOs.InventoryDTO updateInventory(@PathVariable Long id, @RequestBody ProductRelatedDTOs.InventoryDTO inventoryDTO) {
            return inventoryService.updateInventory(id, inventoryDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
            inventoryService.deleteInventory(id);
            return ResponseEntity.ok().build();
        }
    }

    @RestController
    @RequestMapping("/products")
    public static class ProductController {

        private final ProductServices.ProductService productService;

        @Autowired
        public ProductController(ProductServices.ProductService productService) {
            this.productService = productService;
        }

        @GetMapping
        public List<ProductRelatedDTOs.ProductDTO> getAllProducts() {
            return productService.findAllProducts();
        }

        
        @GetMapping("/{id}")
        public ProductRelatedDTOs.ProductDTO getProductById(@PathVariable Long id) {
            return productService.findProductById(id);
        }

        
        @PostMapping
        public ProductRelatedDTOs.ProductDTO addProduct(@RequestBody ProductRelatedDTOs.ProductDTO productDTO) {
            return productService.addProduct(productDTO);
        }

        
        @PutMapping("/{id}")
        public ProductRelatedDTOs.ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductRelatedDTOs.ProductDTO productDTO) {
            return productService.updateProduct(id, productDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        }
    }
}
