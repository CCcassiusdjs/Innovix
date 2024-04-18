package br.com.innovix.dto.product;

import br.com.innovix.entity.product.InventoryEntity;
import br.com.innovix.entity.product.ProductEntity;

import java.util.Date;

public record InventoryDTO(
        Long codInventory,
        Long codProd,
        Integer quantity,
        Date lastUpdate,
        String location
) {
    public static InventoryDTO fromEntity(InventoryEntity entity) {
        return new InventoryDTO(
                entity.getCodInventory(),
                entity.getProduct().getCodProd(),
                entity.getQuantity(),
                entity.getLastUpdate(),
                entity.getLocation()
        );
    }

    public InventoryEntity toEntity(ProductEntity product) {
        InventoryEntity entity = new InventoryEntity();
        entity.setCodInventory(this.codInventory);
        entity.setProduct(product);
        entity.setQuantity(this.quantity);
        entity.setLastUpdate(this.lastUpdate != null ? this.lastUpdate : new Date());
        entity.setLocation(this.location);
        return entity;
    }
}