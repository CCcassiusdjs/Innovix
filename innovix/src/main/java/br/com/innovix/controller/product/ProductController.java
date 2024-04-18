package br.com.innovix.controller.product;

import br.com.innovix.dto.product.ProductDTO;
import br.com.innovix.service.product.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServices.ProductService productService;

    @Autowired
    public ProductController(ProductServices.ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return productService.findAllProducts();
    }


    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }


    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }


    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}