package br.com.innovix.repository;

import br.com.innovix.entity.OrdersEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class OrdersRepositories {

    @Repository
    public interface OrderRepository extends JpaRepository<OrdersEntities.OrderEntity, Long> {
        // Adicione aqui métodos adicionais de consulta, se necessário
    }

    @Repository
    public interface OrderItemsRepository extends JpaRepository<OrdersEntities.OrderItemsEntity, Long> {
        // Adicione aqui métodos adicionais de consulta, se necessário
    }
}
