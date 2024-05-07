package br.com.innovix.domain.order.orderItems;

import br.com.innovix.domain.product.product.ProductEntity;

public record OrderItemsDTO(
        Long codOrderItem,
        Double discount,
        Integer quantity,
        Long codProd,
        Long codOrder  // Assumes there is a field in the super entity for codOrder if needed.
) {
    public static OrderItemsDTO fromEntity(OrderItemsEntity entity) {
        return new OrderItemsDTO(
                entity.getCodOrderItem(), // Assuming getCodOrder() inherited from OrderBase
                entity.getDiscount(),
                entity.getQuantity(),
                entity.getProductByCodProd().getCodProd(),
                entity.getOrder().getCodOrder() // This needs to match with the entity's structure
        );
    }

    public OrderItemsEntity toEntity() {
        OrderItemsEntity entity = new OrderItemsEntity();
        entity.setCodOrderItem(this.codOrder);
        entity.setDiscount(this.discount);
        entity.setQuantity(this.quantity);

        if (this.codProd != null) {
            ProductEntity product = new ProductEntity();
            product.setCodProd(this.codProd);
            entity.setProductByCodProd(product);
        }
        if (this.codOrder != null) {
            // Assuming that OrderBase or a related entity handles codOrder
            entity.setCodOrderItem(this.codOrder);  // Make sure your entity has a setter for codOrder if it's inherited
        }
        return entity;
    }

    public void validateForCreationOrUpdate() {
        if (this.quantity == null || this.quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1 for creation or update.");
        }
        // Additional validations can be included here
    }
}
