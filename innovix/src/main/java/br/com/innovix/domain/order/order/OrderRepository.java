package br.com.innovix.domain.order.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    // Adicione aqui métodos adicionais de consulta, se necessário
}