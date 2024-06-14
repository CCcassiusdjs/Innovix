package br.com.innovix.application.usecase.order;

import br.com.innovix.domain.entity.Order;
import br.com.innovix.domain.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateOrderUseCase {
    private final OrderRepository orderRepository;

    public UpdateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> execute(Long orderId, @Valid Order order) {
        return orderRepository.findById(orderId).map(existingOrder -> {
            existingOrder.setCustomer(order.getCustomer());
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setOriginAddress(order.getOriginAddress());
            existingOrder.setDestinationAddress(order.getDestinationAddress());
            existingOrder.setProducts(order.getProducts());
            existingOrder.setPaymentMethod(order.getPaymentMethod());
            return orderRepository.save(existingOrder);
        });
    }
}
