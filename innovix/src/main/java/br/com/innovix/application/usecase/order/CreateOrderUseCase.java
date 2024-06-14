package br.com.innovix.application.usecase.order;

import br.com.innovix.domain.entity.Order;
import br.com.innovix.domain.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(@Valid Order order) {
        return orderRepository.save(order);
    }
}
