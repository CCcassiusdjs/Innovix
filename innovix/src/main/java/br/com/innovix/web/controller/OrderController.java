package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.order.CreateOrderUseCase;
import br.com.innovix.application.usecase.order.DeleteOrderUseCase;
import br.com.innovix.application.usecase.order.GetOrderUseCase;
import br.com.innovix.application.usecase.order.UpdateOrderUseCase;
import br.com.innovix.domain.entity.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @Autowired
    private GetOrderUseCase getOrderUseCase;

    @Autowired
    private UpdateOrderUseCase updateOrderUseCase;

    @Autowired
    private DeleteOrderUseCase deleteOrderUseCase;

    @PostMapping
    public Order createOrder(@RequestBody @Valid Order order) {
        return createOrderUseCase.execute(order);
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable Long id) {
        return getOrderUseCase.execute(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return getOrderUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<Order> updateOrder(@PathVariable Long id, @RequestBody @Valid Order order) {
        return updateOrderUseCase.execute(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        deleteOrderUseCase.execute(id);
    }
}
