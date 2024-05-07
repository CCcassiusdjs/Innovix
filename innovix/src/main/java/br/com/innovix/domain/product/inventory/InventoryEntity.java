package br.com.innovix.domain.product.inventory;

import br.com.innovix.domain.product.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "inventory", schema = "public", catalog = "innovix")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_inventory", nullable = false)
    private Long codInventory;

    @ManyToOne
    @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
    private ProductEntity product; // Refatorado para simplificar

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Column(name = "location", length = 255)
    private String location;

}
