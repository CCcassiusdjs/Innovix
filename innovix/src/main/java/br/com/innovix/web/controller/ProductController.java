package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.product.CreateProductUseCase;
import br.com.innovix.application.usecase.product.DeleteProductUseCase;
import br.com.innovix.application.usecase.product.GetProductUseCase;
import br.com.innovix.application.usecase.product.UpdateProductUseCase;
import br.com.innovix.domain.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @Autowired
    private GetProductUseCase getProductUseCase;

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @Autowired
    private DeleteProductUseCase deleteProductUseCase;

    @PostMapping
    public Product createProduct(@RequestBody @Valid Product product) {
        return createProductUseCase.execute(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return getProductUseCase.execute(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return getProductUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) {
        return updateProductUseCase.execute(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        deleteProductUseCase.execute(id);
    }
}
