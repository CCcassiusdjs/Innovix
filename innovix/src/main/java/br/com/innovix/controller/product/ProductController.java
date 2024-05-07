package br.com.innovix.controller.product;

import br.com.innovix.domain.product.product.ProductDTO;
import br.com.innovix.domain.product.product.ProductServices;
import br.com.innovix.domain.product.product.ProductServices.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return productService.findAllProducts();
    }


    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable @NotBlank Long id) {
        return productService.findProductById(id);
    }


    @PostMapping
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }


    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable @NotBlank Long id, @RequestBody @Valid ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable @NotBlank Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}