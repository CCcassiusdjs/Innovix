package br.com.innovix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

public class OrdersEntities {

    @Setter
    @Getter
    @Entity
    @Table(name = "order", schema = "public", catalog = "Innovix")
    public static class OrderEntity {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "cod_order", nullable = false)
        private Long codOrder;

        @Basic
        @Column(name = "company_name", nullable = false, length = 255)
        private String companyName;

        @Basic
        @Column(name = "sender", nullable = false, length = 255)
        private String sender;

        @Basic
        @Column(name = "recipient", nullable = false, length = 255)
        private String recipient;

        @Basic
        @Column(name = "ship_cost", nullable = false)
        private double shipCost;

        @Basic
        @Column(name = "state", nullable = false, length = 50)
        private String state;

        @ManyToOne
        @JoinColumn(name = "cod_customer", referencedColumnName = "cod_customer", nullable = false)
        private PersonEntities.CustomerEntity customerByCodCustomer;

        @OneToMany(mappedBy = "orderByCodOrder")
        private Collection<OrderItemsEntity> orderItemsByCodOrder;

        @OneToMany(mappedBy = "orderByCodOrder")
        private Collection<TaxesEntity> taxesByCodOrder;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderEntity that = (OrderEntity) o;
            return codOrder == that.codOrder && Double.compare(that.shipCost, shipCost) == 0 && Objects.equals(companyName, that.companyName) && Objects.equals(sender, that.sender) && Objects.equals(recipient, that.recipient) && Objects.equals(state, that.state) && Objects.equals(customerByCodCustomer, that.customerByCodCustomer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codOrder, companyName, sender, recipient, shipCost, state, customerByCodCustomer);
        }
    }

    @Setter
    @Getter
    @Entity
    @Table(name = "order_items", schema = "public", catalog = "Innovix")
    public static class OrderItemsEntity {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "cod_order_item", nullable = false)
        private Long codOrderItem;

        @Column(name = "discount", precision = 0)
        private Double discount;

        @Column(name = "quantity", nullable = false)
        private int quantity;

        @ManyToOne
        @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
        private ProductEntities.ProductEntity productByCodProd;

        @ManyToOne
        @JoinColumn(name = "cod_order", referencedColumnName = "cod_order", nullable = false)
        private OrderEntity orderByCodOrder;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderItemsEntity that = (OrderItemsEntity) o;
            return codOrderItem == that.codOrderItem && quantity == that.quantity && Objects.equals(discount, that.discount);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codOrderItem, discount, quantity);
        }
    }
}
