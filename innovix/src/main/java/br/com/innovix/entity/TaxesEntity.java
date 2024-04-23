package br.com.innovix.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Data
@Entity
@Table(name = "taxes", schema = "public", catalog = "Innovix")
public class TaxesEntity {

   @Id
   @GeneratedValue
   private Long id;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Basic
    @Column(name = "value", nullable = false, precision = 0)
    private double value;

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;

}
