package com.innovix.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
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
    @Column
    private String size;

    @Size(max = 30)
    private String material;

    @Embedded
    private Dimensions dimensions;

    private String images;

    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Embeddable
    @Data
    public static class Dimensions {
        private double length;
        private double width;
        private double height;
    }
}
