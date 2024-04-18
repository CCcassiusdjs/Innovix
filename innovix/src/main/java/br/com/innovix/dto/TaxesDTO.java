package br.com.innovix.dto;

import br.com.innovix.entity.TaxesEntity;
import br.com.innovix.entity.order.OrderEntity;

public record TaxesDTO(
        String state,
        Double value,
        String description
) {
    public static TaxesDTO fromEntity(TaxesEntity entity) {
        return new TaxesDTO(
                entity.getState(),
                entity.getValue(),
                entity.getDescription()
        );
    }

    public TaxesEntity toEntity() {
        TaxesEntity entity = new TaxesEntity();
        entity.setState(this.state);
        entity.setValue(this.value);
        entity.setDescription(this.description);
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
