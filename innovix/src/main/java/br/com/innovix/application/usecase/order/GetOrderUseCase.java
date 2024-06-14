package br.com.innovix.application.usecase.order;

import br.com.innovix.domain.entity.Order;
import br.com.innovix.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetOrderUseCase {
    private final OrderRepository orderRepository;

    public GetOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> execute(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> execute() {
        return orderRepository.findAll();
    }
}
