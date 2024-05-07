package br.com.innovix.domain.order.order;

import br.com.innovix.domain.person.PersonEntity;

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
                entity.getCodOrder(),
                entity.getCompanyName(),
                entity.getSender(),
                entity.getRecipient(),
                entity.getShipCost(),
                entity.getState(),
                entity.getCustomer().getId()  // Assuming there's a getCustomer method in OrderEntity
        );
    }

    public OrderEntity toEntity() {
        OrderEntity entity = new OrderEntity();
        entity.setCodOrder(this.codOrder);
        entity.setCompanyName(this.companyName);
        entity.setSender(this.sender);
        entity.setRecipient(this.recipient);
        entity.setShipCost(this.shipCost);
        entity.setState(this.state);
        if (this.codCustomer != null) {
            PersonEntity customer = new PersonEntity();
            customer.setId(this.codCustomer);
            entity.setCustomer(customer);
        }
        return entity;
    }
}
