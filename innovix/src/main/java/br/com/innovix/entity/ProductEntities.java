package br.com.innovix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class ProductEntities {

    @Setter
    @Getter
    @Entity
    @Table(name = "inventory", schema = "public", catalog = "Innovix")
    public static class InventoryEntity {
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
            return Objects.equals(codInventory, that.codInventory) &&
                    Objects.equals(productByCodProd, that.productByCodProd) &&
                    Objects.equals(quantity, that.quantity) &&
                    Objects.equals(lastUpdate, that.lastUpdate) &&
                    Objects.equals(location, that.location);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codInventory, productByCodProd, quantity, lastUpdate, location);
        }
    }

    @Setter
    @Getter
    @Entity
    @Table(name = "product", schema = "public", catalog = "Innovix")
    public static class ProductEntity {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "cod_prod", nullable = false)
        private Long codProd;
        @Basic
        @Column(name = "image", nullable = true)
        private String image;
        @Basic
        @Column(name = "description", nullable = false, length = 255)
        private String description;
        @Basic
        @Column(name = "name", nullable = false, length = 255)
        private String name;
        @Basic
        @Column(name = "quantity", nullable = false)
        private int quantity;
        @OneToMany(mappedBy = "productByCodProd")
        private Collection<InventoryEntity> inventories;
        @OneToMany(mappedBy = "productByCodProd")
        private Collection<SalesEntities.SaleItemsEntity> saleItemsByCodProd;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProductEntity that = (ProductEntity) o;
            return codProd.equals(that.codProd) &&
                    Objects.equals(image, that.image) &&
                    Objects.equals(description, that.description) &&
                    Objects.equals(name, that.name) &&
                    quantity == that.quantity;
        }

        @Override
        public int hashCode() {
            return Objects.hash(codProd, image, description, name, quantity);
        }
    }
}
