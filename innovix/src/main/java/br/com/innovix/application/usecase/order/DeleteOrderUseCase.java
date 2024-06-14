package br.com.innovix.application.usecase.order;

import br.com.innovix.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderUseCase {
    private final OrderRepository orderRepository;

    public DeleteOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
