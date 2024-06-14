package br.com.innovix.application.usecase.product;

import br.com.innovix.domain.entity.Product;
import br.com.innovix.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetProductUseCase {
    private final ProductRepository productRepository;

    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> execute(Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> execute() {
        return productRepository.findAll();
    }
}
