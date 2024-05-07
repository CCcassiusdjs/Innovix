package br.com.innovix.domain.order;

import br.com.innovix.domain.person.PersonEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class OrderBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_order", nullable = false)
    protected Long codOrder;

    @Column(name = "company_name", nullable = false, length = 255)
    protected String companyName;

    @Column(name = "sender", nullable = false, length = 255)
    protected String sender;

    @Column(name = "recipient", nullable = false, length = 255)
    protected String recipient;

    @Column(name = "ship_cost", nullable = false)
    protected double shipCost;

    @Column(name = "state", nullable = false, length = 50)
    protected String state;

    @ManyToOne
    @JoinColumn(name = "cod_customer", referencedColumnName = "id")
    protected PersonEntity customer;
}
