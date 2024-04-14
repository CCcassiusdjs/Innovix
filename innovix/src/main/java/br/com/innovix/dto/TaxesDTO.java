package br.com.innovix.dto;

import br.com.innovix.entity.OrdersEntities;
import br.com.innovix.entity.TaxesEntity;

public record TaxesDTO(
        Long codTax,
        String state,
        Double value,
        String description,
        Long codOrder
) {
    public static TaxesDTO fromEntity(TaxesEntity entity) {
        return new TaxesDTO(
                (long) entity.getCodTax(),
                entity.getState(),
                entity.getValue(),
                entity.getDescription(),
                (long) entity.getOrderByCodOrder().getCodOrder()
        );
    }

    public TaxesEntity toEntity() {
        TaxesEntity entity = new TaxesEntity();
        entity.setCodTax(this.codTax);
        entity.setState(this.state);
        entity.setValue(this.value);
        entity.setDescription(this.description);
        OrdersEntities.OrderEntity orderEntity = new OrdersEntities.OrderEntity();
        orderEntity.setCodOrder(this.codOrder);
        entity.setOrderByCodOrder(orderEntity);
        return entity;
    }

    public void validateForCreationOrUpdate() {
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado é obrigatório.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Valor é obrigatório.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória.");
        }
        // Validar codOrder se necessário
    }
}
