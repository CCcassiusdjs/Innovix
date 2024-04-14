package br.com.innovix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

public class SalesEntities {

    @Setter
    @Getter
    @Entity
    @Table(name = "sale", schema = "public", catalog = "Innovix")
    public static class SaleEntity {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "cod_sale", nullable = false)
        private Long codSale;
        @Basic
        @Column(name = "date", nullable = false)
        private Date date;
        @Column(name = "start_date", nullable = false)
        private Date startDate;
        @Column(name = "end_date", nullable = false)
        private Date endDate;
        @Basic
        @Column(name = "type", nullable = false, length = 255)
        private String type;
        @OneToMany(mappedBy = "saleByCodSale")
        private Collection<SaleItemsEntity> saleItemsByCodSale;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SaleEntity that = (SaleEntity) o;
            return Objects.equals(codSale, that.codSale) &&
                    Objects.equals(date, that.date) &&
                    Objects.equals(startDate, that.startDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codSale, date, startDate, endDate, type);
        }
    }

    @Setter
    @Getter
    @Entity
    @Table(name = "sale_items", schema = "public", catalog = "Innovix")
    public static class SaleItemsEntity {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "cod_sale_item", nullable = false)
        private Long codSaleItem;
        @Column(name = "description", nullable = false, length = 255)
        private String description;
        @Column(name = "price", nullable = false)
        private double price;
        @Column(name = "quantity", nullable = false)
        private int quantity;
        @ManyToOne
        @JoinColumn(name = "cod_sale", referencedColumnName = "cod_sale", nullable = false)
        private SaleEntity saleByCodSale;
        @ManyToOne
        @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
        private ProductEntities.ProductEntity productByCodProd;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SaleItemsEntity that = (SaleItemsEntity) o;
            return Objects.equals(codSaleItem, that.codSaleItem) &&
                    Double.compare(price, that.price) == 0 &&
                    quantity == that.quantity &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codSaleItem, description, price, quantity);
        }
    }
}
