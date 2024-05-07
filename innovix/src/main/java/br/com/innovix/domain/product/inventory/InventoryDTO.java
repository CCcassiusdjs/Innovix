package br.com.innovix.domain.product.inventory;

import br.com.innovix.domain.product.product.ProductEntity;

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