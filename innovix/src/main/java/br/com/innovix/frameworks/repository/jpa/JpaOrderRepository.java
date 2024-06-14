package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.Order;
import br.com.innovix.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long>, OrderRepository {
}
