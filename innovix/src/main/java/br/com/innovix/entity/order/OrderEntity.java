package br.com.innovix.entity.order;

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
