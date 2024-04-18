package br.com.innovix.service.product;

import br.com.innovix.dto.product.InventoryDTO;
import br.com.innovix.dto.product.ProductDTO;
import br.com.innovix.entity.product.InventoryEntity;
import br.com.innovix.entity.product.ProductEntity;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.repository.product.InventoryRepository;
import br.com.innovix.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServices {

    @Service
    public static class InventoryService {

        @Autowired
        private InventoryRepository inventoryRepository;

        public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
            InventoryEntity inventoryEntity = inventoryRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.InventoryException("Item de estoque não encontrado com ID: " + id));

            inventoryEntity.setQuantity(inventoryDTO.quantity());
            inventoryEntity.setLastUpdate(inventoryDTO.lastUpdate());
            inventoryEntity.setLocation(inventoryDTO.location());

            return InventoryDTO.fromEntity(inventoryRepository.save(inventoryEntity));
        }

        public void deleteInventory(Long id) {
            InventoryEntity inventoryEntity = inventoryRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.InventoryException("Item de estoque não encontrado com ID: " + id));
            inventoryRepository.delete(inventoryEntity);
        }
    }

    @Service
    public static class ProductService {

        @Autowired
        private ProductRepository productRepository;

        public List<ProductDTO> findAllProducts() {
            return productRepository.findAll().stream()
                    .map(ProductDTO::fromEntity)
                    .collect(Collectors.toList());
        }

        public ProductDTO findProductById(Long id) {
            ProductEntity product = productRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ProductNotFoundException("Produto não encontrado com o ID: " + id));
            return ProductDTO.fromEntity(product);
        }

        public ProductDTO addProduct(ProductDTO productDTO) {
            ProductEntity product = new ProductEntity();
            updateProductFields(product, productDTO);
            return ProductDTO.fromEntity(productRepository.save(product));
        }

        public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
            ProductEntity existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ProductNotFoundException("Produto não encontrado com o ID: " + id));
            updateProductFields(existingProduct, productDTO);
            return ProductDTO.fromEntity(productRepository.save(existingProduct));
        }

        public void deleteProduct(Long id) {
            ProductEntity product = productRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ProductNotFoundException("Produto não encontrado com o ID: " + id));
            productRepository.delete(product);
        }

        private void updateProductFields(ProductEntity product, ProductDTO productDTO) {
            product.setName(productDTO.name());
            product.setDescription(productDTO.description());
            product.setImage(productDTO.image());
            product.setQuantity(productDTO.quantity());
        }
    }
}
