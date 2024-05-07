package br.com.innovix.domain.person;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person_type", schema = "public", catalog = "innovix")
public class PersonTypeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;  // 'Employee' ou 'Customer'

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "access_level", nullable = false)
    private Integer accessLevel;
}
