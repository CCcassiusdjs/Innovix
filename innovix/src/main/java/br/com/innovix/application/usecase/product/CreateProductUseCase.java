package br.com.innovix.application.usecase.product;

import br.com.innovix.domain.entity.Product;
import br.com.innovix.domain.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(@Valid Product product) {
        return productRepository.save(product);
    }
}
