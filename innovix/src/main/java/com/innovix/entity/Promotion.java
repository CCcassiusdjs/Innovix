package com.innovix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String description;

    @NotNull
    @Size(max = 6)
    @Column(nullable = false)
    private String season;

    @Temporal(TemporalType.DATE)
    private Date initDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Integer duration;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double percentage;

    private int requiredQuantity;  // NEW: Required quantity for the promotion
    private int freeQuantity;      // NEW: Free quantity given

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Person employee;
}
