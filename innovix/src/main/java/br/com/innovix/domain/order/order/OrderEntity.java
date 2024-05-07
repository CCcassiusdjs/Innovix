package br.com.innovix.domain.order.order;

import br.com.innovix.domain.order.OrderBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "orders", schema = "public", catalog = "innovix")
public class OrderEntity extends OrderBase {
    // Adicione campos específicos adicionais para OrderEntity, se necessário.
}
