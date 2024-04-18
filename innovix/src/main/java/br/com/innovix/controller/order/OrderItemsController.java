package br.com.innovix.controller.order;

import br.com.innovix.dto.order.OrderItemsDTO;
import br.com.innovix.service.order.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    @Autowired
    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemsDTO> getOrderItemsById(@PathVariable Long id) {
        OrderItemsDTO orderItem = orderItemsService.findOrderById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemsDTO> updateOrderItem(@PathVariable Long id, @Validated @RequestBody OrderItemsDTO orderItemsDTO) {
        OrderItemsDTO updatedOrderItem = orderItemsService.updateOrderItem(id, orderItemsDTO);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
        orderItemsService.deleteOrderItem(id);
        return ResponseEntity.ok().build();
    }
}