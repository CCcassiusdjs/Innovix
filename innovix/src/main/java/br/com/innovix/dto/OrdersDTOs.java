package br.com.innovix.dto;

import br.com.innovix.entity.OrdersEntities;
import br.com.innovix.entity.PersonEntities;
import br.com.innovix.entity.ProductEntities;

public class OrdersDTOs {

    public record OrderDTO(
            Long codOrder,
            String companyName,
            String sender,
            String recipient,
            Double shipCost,
            String state,
            Long codCustomer
    ) {
        public static OrderDTO fromEntity(OrdersEntities.OrderEntity entity) {
            return new OrderDTO(
                    entity.getCodOrder(),
                    entity.getCompanyName(),
                    entity.getSender(),
                    entity.getRecipient(),
                    entity.getShipCost(),
                    entity.getState(),
                    entity.getCustomerByCodCustomer().getCodCustomer()
            );
        }

        public OrdersEntities.OrderEntity toEntity() {
            OrdersEntities.OrderEntity entity = new OrdersEntities.OrderEntity();
            entity.setCodOrder(this.codOrder);
            entity.setCompanyName(this.companyName);
            entity.setSender(this.sender);
            entity.setRecipient(this.recipient);
            entity.setShipCost(this.shipCost);
            entity.setState(this.state);
            PersonEntities.CustomerEntity customerEntity = new PersonEntities.CustomerEntity();
            customerEntity.setCodCustomer(this.codCustomer);
            entity.setCustomerByCodCustomer(customerEntity);
            return entity;
        }
    }

    public record OrderItemsDTO(
            Long codOrderItem,
            Double discount,
            Integer quantity,
            Long codProd,
            Long codOrder
    ) {
        public static OrderItemsDTO fromEntity(OrdersEntities.OrderItemsEntity entity) {
            return new OrderItemsDTO(
                    entity.getCodOrderItem(),
                    entity.getDiscount(),
                    entity.getQuantity(),
                    entity.getProductByCodProd().getCodProd(),
                    entity.getOrderByCodOrder().getCodOrder()
            );
        }

        public OrdersEntities.OrderItemsEntity toEntity() {
            OrdersEntities.OrderItemsEntity entity = new OrdersEntities.OrderItemsEntity();
            entity.setCodOrderItem(this.codOrderItem);
            entity.setDiscount(this.discount);
            entity.setQuantity(this.quantity);
            ProductEntities.ProductEntity productEntity = new ProductEntities.ProductEntity();
            productEntity.setCodProd(this.codProd);
            entity.setProductByCodProd(productEntity);
            OrdersEntities.OrderEntity orderEntity = new OrdersEntities.OrderEntity();
            orderEntity.setCodOrder(this.codOrder);
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
}
