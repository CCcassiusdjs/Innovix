package br.com.innovix.dto;

import br.com.innovix.entity.OrderEntity;
import br.com.innovix.entity.CustomerEntity;

public record OrderDTO(
        Long codOrder,
        String companyName,
        String sender,
        String recipient,
        Double shipCost,
        String state,
        Long codCustomer
) {
    public static OrderDTO fromEntity(OrderEntity entity) {
        return new OrderDTO(
                (long) entity.getCodOrder(),
                entity.getCompanyName(),
                entity.getSender(),
                entity.getRecipient(),
                entity.getShipCost(),
                entity.getState(),
                (long) entity.getCustomerByCodCustomer().getCodCustomer()
        );
    }

    public OrderEntity toEntity() {
        OrderEntity entity = new OrderEntity();
        entity.setCodOrder(Math.toIntExact(this.codOrder));
        entity.setCompanyName(this.companyName);
        entity.setSender(this.sender);
        entity.setRecipient(this.recipient);
        entity.setShipCost(this.shipCost);
        entity.setState(this.state);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCodCustomer(Math.toIntExact(this.codCustomer));
        entity.setCustomerByCodCustomer(customerEntity);
        return entity;
    }
}
