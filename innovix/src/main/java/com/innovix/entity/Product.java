package com.innovix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @Size(max = 20)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 2)
    @Column(nullable = true)
    private String size;

    @Size(max = 30)
    private String material;

    @Embedded
    private Dimensions dimensions;

    private String images;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Embeddable
    @Data
    public static class Dimensions {
        private Double length;
        private Double width;
        private Double height;
    }
}
