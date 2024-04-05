package br.com.innovix.controller;

import br.com.innovix.dto.ProductDTO;
import br.com.innovix.entity.ProductEntity;
import br.com.innovix.exception.ProductNotFoundException;
import br.com.innovix.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o ID: " + id));
        return ProductDTO.fromEntity(product);
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        // Validação pode ser adicionada aqui, se necessário
        ProductEntity product = new ProductEntity();
        // Defina os campos do produto aqui com base em productDTO
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setImage(productDTO.image());
        product.setQuantity(productDTO.quantity());

        ProductEntity savedProduct = productRepository.save(product);
        return ProductDTO.fromEntity(savedProduct);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o ID: " + id));
        // Atualize existingProduct aqui com base em productDTO
        existingProduct.setName(productDTO.name());
        existingProduct.setDescription(productDTO.description());
        existingProduct.setImage(productDTO.image());
        existingProduct.setQuantity(productDTO.quantity());

        ProductEntity updatedProduct = productRepository.save(existingProduct);
        return ProductDTO.fromEntity(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o ID: " + id));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
