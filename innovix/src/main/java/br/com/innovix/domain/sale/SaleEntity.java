package br.com.innovix.domain.sale;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Collection;

@Data
@Entity
@Table(name = "sale", schema = "public", catalog = "Innovix")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_sale", nullable = false)
    private Long codSale;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "type", nullable = false, length = 255)
    private String type;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<SaleItemsEntity> saleItems;
}
