package br.com.innovix.entity.sale;

import br.com.innovix.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sale_items", schema = "public", catalog = "Innovix")
public class SaleItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_sale_item", nullable = false)
    private Long codSaleItem;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_sale", referencedColumnName = "cod_sale", nullable = false)
    private SaleEntity sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
    private ProductEntity product;
}