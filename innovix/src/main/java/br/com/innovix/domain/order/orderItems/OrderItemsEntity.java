package br.com.innovix.domain.order.orderItems;

import br.com.innovix.domain.order.order.OrderEntity;
import br.com.innovix.domain.product.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items", schema = "public", catalog = "innovix")
public class OrderItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_order_item", nullable = false)
    private Long codOrderItem;

    @ManyToOne
    @JoinColumn(name = "cod_order", referencedColumnName = "cod_order", nullable = false)
    private OrderEntity order;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "discount", precision = 0)
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
    private ProductEntity productByCodProd;
}
