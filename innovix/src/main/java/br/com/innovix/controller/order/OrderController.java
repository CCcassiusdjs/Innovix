package br.com.innovix.controller.order;

import br.com.innovix.dto.order.OrderDTO;
import br.com.innovix.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.findOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@Validated @RequestBody OrderDTO orderDTO) {
        OrderDTO newOrder = orderService.addOrder(orderDTO);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @Validated @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
