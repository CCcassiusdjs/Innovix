package com.innovix.entity;

import javax.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String description;

    @NotNull
    @Size(max = 6)
    @Column(nullable = false)
    private String season;

    @Temporal(TemporalType.DATE)
    private LocalDate initLocalDate;

    @Temporal(TemporalType.DATE)
    private LocalDate endLocalDate;

    private int duration;

    @Positive
    private double percentage;

    private int requiredQuantity;  // NEW: Required quantity for the promotion
    private int freeQuantity;      // NEW: Free quantity given

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Person employee;
}
