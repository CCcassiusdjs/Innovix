package br.com.innovix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "inventory", schema = "public", catalog = "Innovix")
public class InventoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cod_inventory", nullable = false)
    private Long codInventory;

    @ManyToOne
    @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
    private ProductEntity productByCodProd;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Column(name = "location", length = 255)
    private String location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntity that = (InventoryEntity) o;
        return Objects.equals(codInventory, that.codInventory) && Objects.equals(productByCodProd, that.productByCodProd) && Objects.equals(quantity, that.quantity) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codInventory, productByCodProd, quantity, lastUpdate, location);
    }
}
