package br.com.innovix.dto;

import br.com.innovix.entity.ProductEntities;

import java.util.Date;

public class ProductRelatedDTOs {

    public record ProductDTO(
            Long codProd,
            String image, // Adjust as necessary depending on how you handle images or XML
            String description,
            String name,
            Integer quantity
    ) {
        public static ProductDTO fromEntity(ProductEntities.ProductEntity entity) {
            return new ProductDTO(
                    entity.getCodProd(),
                    entity.getImage(),
                    entity.getDescription(),
                    entity.getName(),
                    entity.getQuantity()
            );
        }

        public void validateForCreationOrUpdate() {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome é obrigatório para o produto.");
            }
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição é obrigatória para o produto.");
            }
            // Additional validations as necessary
        }
    }

    public record InventoryDTO(
            Long codInventory,
            Long codProd,
            Integer quantity,
            Date lastUpdate,
            String location
    ) {
        public static InventoryDTO fromEntity(ProductEntities.InventoryEntity entity) {
            return new InventoryDTO(
                    entity.getCodInventory(),
                    entity.getProductByCodProd().getCodProd(),
                    entity.getQuantity(),
                    entity.getLastUpdate(),
                    entity.getLocation()
            );
        }

        public ProductEntities.InventoryEntity toEntity() {
            ProductEntities.InventoryEntity entity = new ProductEntities.InventoryEntity();
            entity.setCodInventory(this.codInventory);
            ProductEntities.ProductEntity productEntity = new ProductEntities.ProductEntity();
            productEntity.setCodProd(this.codProd);
            entity.setProductByCodProd(productEntity);
            entity.setQuantity(this.quantity);
            entity.setLastUpdate(this.lastUpdate != null ? this.lastUpdate : new Date());
            entity.setLocation(this.location);
            return entity;
        }
    }
}
