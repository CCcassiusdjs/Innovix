package br.com.innovix.dto;

import br.com.innovix.entity.OrderItemsEntity;
import br.com.innovix.entity.ProductEntity;
import br.com.innovix.entity.OrderEntity;

public record OrderItemsDTO(
        Long codOrderItem,
        Double discount,
        Integer quantity,
        Long codProd,
        Long codOrder
) {
    public static OrderItemsDTO fromEntity(OrderItemsEntity entity) {
        return new OrderItemsDTO(
                (long) entity.getCodOrderItem(),
                entity.getDiscount(),
                entity.getQuantity(),
                (long) entity.getProductByCodProd().getCodProd(),
                (long) entity.getOrderByCodOrder().getCodOrder()
        );
    }

    public OrderItemsEntity toEntity() {
        OrderItemsEntity entity = new OrderItemsEntity();
        entity.setCodOrderItem(Math.toIntExact(this.codOrderItem));
        entity.setDiscount(this.discount);
        entity.setQuantity(this.quantity);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCodProd(Math.toIntExact(this.codProd));
        entity.setProductByCodProd(productEntity);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCodOrder(Math.toIntExact(this.codOrder));
        entity.setOrderByCodOrder(orderEntity);
        return entity;
    }

    public void validateForCreationOrUpdate() {
        if (this.quantity == null) {
            throw new IllegalArgumentException("Quantidade é obrigatória para a criação ou atualização do item de pedido.");
        }
        if (this.quantity < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
        // Validar codProd e codOrder, se necessário
    }
}
