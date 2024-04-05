package br.com.innovix.dto;

import br.com.innovix.entity.TaxesEntity;
import br.com.innovix.entity.OrderEntity;

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
        entity.setCodTax(Math.toIntExact(this.codTax));
        entity.setState(this.state);
        entity.setValue(this.value);
        entity.setDescription(this.description);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCodOrder(Math.toIntExact(this.codOrder));
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
