package com.innovix.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promotion")
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

    private int duration;

    @Positive
    private double percentage;

    private int requiredQuantity;  // NEW: Required quantity for the promotion
    private int freeQuantity;      // NEW: Free quantity given

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Person employee;
}
