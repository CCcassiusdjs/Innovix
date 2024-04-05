package br.com.innovix.dto;

import br.com.innovix.entity.CustomerEntity;

public record CustomerDTO(
        Long codCustomer,
        String name,
        String street,
        String zipCode
) {
    public static CustomerDTO fromEntity(CustomerEntity entity) {
        return new CustomerDTO(
                (long) entity.getCodCustomer(),
                entity.getName(),
                entity.getStreet(),
                entity.getZipCode()
        );
    }

    public CustomerEntity toEntity() {
        CustomerEntity entity = new CustomerEntity();
        // Configurar o entity com base nos campos do DTO
        entity.setCodCustomer(Math.toIntExact(this.codCustomer));
        entity.setName(this.name);
        entity.setStreet(this.street);
        entity.setZipCode(this.zipCode);
        return entity;
    }

    public void validateForCreationOrUpdate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Rua é obrigatória.");
        }
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Código postal é obrigatório.");
        }
        // Outras validações podem ser adicionadas conforme necessário
    }
}
