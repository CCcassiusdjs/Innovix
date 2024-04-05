package br.com.innovix.dto;

import br.com.innovix.entity.InventoryEntity;
import br.com.innovix.entity.ProductEntity;
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
                (long) entity.getProductByCodProd().getCodProd(),
                entity.getQuantity(),
                entity.getLastUpdate(),
                entity.getLocation()
        );
    }

    public InventoryEntity toEntity() {
        InventoryEntity entity = new InventoryEntity();
        entity.setCodInventory(this.codInventory);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCodProd(Math.toIntExact(this.codProd));
        entity.setProductByCodProd(productEntity);
        entity.setQuantity(this.quantity);
        entity.setLastUpdate(this.lastUpdate != null ? this.lastUpdate : new Date());
        entity.setLocation(this.location);
        return entity;
    }
}
