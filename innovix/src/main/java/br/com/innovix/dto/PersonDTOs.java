package br.com.innovix.dto;

import br.com.innovix.entity.PersonEntities;

public class PersonDTOs {

    public record CustomerDTO(
            Long codCustomer,
            String name,
            String street,
            String zipCode
    ) {
        public static CustomerDTO fromEntity(PersonEntities.CustomerEntity entity) {
            return new CustomerDTO(
                    entity.getCodCustomer(),
                    entity.getName(),
                    entity.getStreet(),
                    entity.getZipCode()
            );
        }

        public PersonEntities.CustomerEntity toEntity() {
            PersonEntities.CustomerEntity entity = new PersonEntities.CustomerEntity();
            entity.setCodCustomer(this.codCustomer);
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
        }
    }

    public record EmployeeDTO(
            Long codEmployee,
            String name,
            String street,
            String zipCode
    ) {
        public static EmployeeDTO fromEntity(PersonEntities.EmployeeEntity entity) {
            return new EmployeeDTO(
                    entity.getCodEmployee(),
                    entity.getName(),
                    entity.getStreet(),
                    entity.getZipCode()
            );
        }

        public PersonEntities.EmployeeEntity toEntity() {
            PersonEntities.EmployeeEntity entity = new PersonEntities.EmployeeEntity();
            entity.setCodEmployee(this.codEmployee);
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
        }
    }
}
