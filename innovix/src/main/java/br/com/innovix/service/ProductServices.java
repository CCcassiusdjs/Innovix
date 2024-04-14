package br.com.innovix.service;

import br.com.innovix.dto.ProductRelatedDTOs;
import br.com.innovix.entity.ProductEntities;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.repository.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServices {

    @Service
    public static class InventoryService {

        @Autowired
        private ProductRepositories.InventoryRepository inventoryRepository;

        public ProductRelatedDTOs.InventoryDTO updateInventory(Long id, ProductRelatedDTOs.InventoryDTO inventoryDTO) {
            ProductEntities.InventoryEntity inventoryEntity = inventoryRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.InventoryException("Item de estoque não encontrado com ID: " + id));

            inventoryEntity.setQuantity(inventoryDTO.quantity());
            inventoryEntity.setLastUpdate(inventoryDTO.lastUpdate());
            inventoryEntity.setLocation(inventoryDTO.location());

            return ProductRelatedDTOs.InventoryDTO.fromEntity(inventoryRepository.save(inventoryEntity));
        }

        public void deleteInventory(Long id) {
            ProductEntities.InventoryEntity inventoryEntity = inventoryRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.InventoryException("Item de estoque não encontrado com ID: " + id));
            inventoryRepository.delete(inventoryEntity);
        }
    }

    @Service
    public static class ProductService {

        @Autowired
        private ProductRepositories.ProductRepository productRepository;

        public List<ProductRelatedDTOs.ProductDTO> findAllProducts() {
            return productRepository.findAll().stream()
                    .map(ProductRelatedDTOs.ProductDTO::fromEntity)
                    .collect(Collectors.toList());
        }

        public ProductRelatedDTOs.ProductDTO findProductById(Long id) {
            ProductEntities.ProductEntity product = productRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ProductNotFoundException("Produto não encontrado com o ID: " + id));
            return ProductRelatedDTOs.ProductDTO.fromEntity(product);
        }

        public ProductRelatedDTOs.ProductDTO addProduct(ProductRelatedDTOs.ProductDTO productDTO) {
            ProductEntities.ProductEntity product = new ProductEntities.ProductEntity();
            updateProductFields(product, productDTO);
            return ProductRelatedDTOs.ProductDTO.fromEntity(productRepository.save(product));
        }

        public ProductRelatedDTOs.ProductDTO updateProduct(Long id, ProductRelatedDTOs.ProductDTO productDTO) {
            ProductEntities.ProductEntity existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ProductNotFoundException("Produto não encontrado com o ID: " + id));
            updateProductFields(existingProduct, productDTO);
            return ProductRelatedDTOs.ProductDTO.fromEntity(productRepository.save(existingProduct));
        }

        public void deleteProduct(Long id) {
            ProductEntities.ProductEntity product = productRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ProductNotFoundException("Produto não encontrado com o ID: " + id));
            productRepository.delete(product);
        }

        private void updateProductFields(ProductEntities.ProductEntity product, ProductRelatedDTOs.ProductDTO productDTO) {
            product.setName(productDTO.name());
            product.setDescription(productDTO.description());
            product.setImage(productDTO.image());
            product.setQuantity(productDTO.quantity());
        }
    }
}
