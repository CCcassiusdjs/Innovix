package br.com.innovix.dto;

import br.com.innovix.entity.ProductEntity;

public record ProductDTO(
        Long codProd,
        String image, // O tipo pode precisar ser ajustado dependendo de como você trata XML
        String description,
        String name,
        Integer quantity
) {
    public static ProductDTO fromEntity(ProductEntity entity) {
        return new ProductDTO(
                (long) entity.getCodProd(),
                entity.getImage(), // Ajuste conforme necessário para o tratamento de XML
                entity.getDescription(),
                entity.getName(),
                entity.getQuantity()
        );
    }

    public void validateForCreationOrUpdate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório para o produto.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória para o produto.");
        }
        // Outras validações conforme necessário
    }

    // Método toEntity() removido devido à natureza imutável do record
}
