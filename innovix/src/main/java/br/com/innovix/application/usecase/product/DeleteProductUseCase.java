package br.com.innovix.application.usecase.product;

import br.com.innovix.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCase {
    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Long productId) {
        productRepository.deleteById(productId);
    }
}
