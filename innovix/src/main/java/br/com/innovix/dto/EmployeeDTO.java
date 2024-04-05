package br.com.innovix.dto;

import br.com.innovix.entity.EmployeeEntity;

public record EmployeeDTO(
        Long codEmployee,
        String name,
        String street,
        String zipCode
) {
    public static EmployeeDTO fromEntity(EmployeeEntity entity) {
        return new EmployeeDTO(
                (long) entity.getCodEmployee(),
                entity.getName(),
                entity.getStreet(),
                entity.getZipCode()
        );
    }

    public EmployeeEntity toEntity() {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setCodEmployee(Math.toIntExact(this.codEmployee));
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
