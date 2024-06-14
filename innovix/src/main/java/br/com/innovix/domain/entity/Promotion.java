package br.com.innovix.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date should be in the present or future")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date should be in the present or future")
    private LocalDateTime endDate;

    @ManyToMany(mappedBy = "promotions")
    private Set<Product> products;
}
