package br.com.innovix.domain.order.orderItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Long> {
    // Adicione aqui métodos adicionais de consulta, se necessário
}