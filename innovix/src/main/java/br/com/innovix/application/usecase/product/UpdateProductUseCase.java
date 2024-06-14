package br.com.innovix.application.usecase.product;

import br.com.innovix.domain.entity.Product;
import br.com.innovix.domain.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> execute(Long productId, @Valid Product product) {
        return productRepository.findById(productId).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPromotions(product.getPromotions());
            return productRepository.save(existingProduct);
        });
    }
}
