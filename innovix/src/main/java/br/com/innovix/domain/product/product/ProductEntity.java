package br.com.innovix.domain.product.product;

import br.com.innovix.domain.product.inventory.InventoryEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Entity
@Table(name = "product", schema = "public", catalog = "innovix")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_prod", nullable = false)
    private Long codProd;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "product")
    private Collection<InventoryEntity> inventories;

    // Campos para gerenciamento de promoções
    @Column(name = "is_promotion_active", nullable = false)
    private boolean isPromotionActive;

    @Column(name = "promotion_end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate promotionEndDate;

    @Column(name = "promotion_type", length = 50)
    private String promotionType;

    @Column(name = "minimum_quantity_for_promotion")
    private int minimumQuantityForPromotion;

    @Column(name = "promotion_price")
    private Double promotionPrice;

}
