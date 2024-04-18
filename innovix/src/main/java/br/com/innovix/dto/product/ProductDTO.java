package br.com.innovix.dto.product;

import br.com.innovix.entity.product.ProductEntity;


public record ProductDTO(
        Long codProd,
        String image,
        String description,
        String name,
        Integer quantity  // Representa a quantidade total do produto em todos os inventários.
) {
    // Método para converter a entidade ProductEntity para ProductDTO.
    public static ProductDTO fromEntity(ProductEntity entity) {
        return new ProductDTO(
                entity.getCodProd(),
                entity.getImage(),
                entity.getDescription(),
                entity.getName(),
                entity.getQuantity()
        );
    }

    // Método para validar o DTO antes de uma operação de criação ou atualização.
    public void validateForCreationOrUpdate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório para o produto.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória para o produto.");
        }
        // Você pode adicionar outras validações conforme necessário.
    }
}