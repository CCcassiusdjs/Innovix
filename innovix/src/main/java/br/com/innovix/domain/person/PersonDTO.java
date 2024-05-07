package br.com.innovix.domain.person;

public record PersonDTO(
        Long id,
        String name,
        String street,
        String zipCode,
        String type, // "CUSTOMER" ou "EMPLOYEE"
        String additionalInfo // informações adicionais, diferenciando clientes e funcionários se necessário
) {
    public static PersonDTO fromEntity(Object entity) {
        if (entity instanceof PersonEntity personEntity) {
            PersonTypeEntity personTypeEntity = new PersonTypeEntity();
            String type = personTypeEntity.getId();  // Assuming getType() method exists to determine the type

            if (type.equals("CUSTOMER")) {
                return new PersonDTO(
                        personEntity.getId(),
                        personEntity.getName(),
                        personEntity.getStreet(),
                        personEntity.getZipCode(),
                        "CUSTOMER",
                        null  // Additional customer-specific logic here
                );
            } else if (type.equals("EMPLOYEE")) {
                return new PersonDTO(
                        personEntity.getId(),
                        personEntity.getName(),
                        personEntity.getStreet(),
                        personEntity.getZipCode(),
                        "EMPLOYEE",
                        null  // Additional employee-specific logic here
                );
            } else {
                throw new IllegalArgumentException("Unknown entity type: " + type);
            }
        }
        throw new IllegalArgumentException("Invalid entity instance");
    }

    public Object toEntity() {
        if (this.type.equalsIgnoreCase("CUSTOMER")) {
            PersonEntity customerEntity = new PersonEntity();
            customerEntity.setId(this.id);
            customerEntity.setName(this.name);
            customerEntity.setStreet(this.street);
            customerEntity.setZipCode(this.zipCode);
            return customerEntity;
        } else if (this.type.equalsIgnoreCase("EMPLOYEE")) {
            PersonEntity employeeEntity = new PersonEntity();
            employeeEntity.setId(this.id);
            employeeEntity.setName(this.name);
            employeeEntity.setStreet(this.street);
            employeeEntity.setZipCode(this.zipCode);
            return employeeEntity;
        }
        throw new IllegalArgumentException("Invalid type specified");
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
        if (type == null || (!type.equalsIgnoreCase("CUSTOMER") && !type.equalsIgnoreCase("EMPLOYEE"))) {
            throw new IllegalArgumentException("Tipo de pessoa é obrigatório e deve ser 'CUSTOMER' ou 'EMPLOYEE'.");
        }
        // Validações adicionais para `additionalInfo` se necessário
    }
}
