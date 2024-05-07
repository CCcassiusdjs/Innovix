package br.com.innovix.domain.person;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person", schema = "public", catalog = "Innovix")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "street", nullable = false, length = 255)
    private String street;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "person_type_id", referencedColumnName = "id")
    private PersonTypeEntity personType;
}
