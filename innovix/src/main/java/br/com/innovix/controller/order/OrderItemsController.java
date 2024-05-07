package br.com.innovix.controller.order;

import br.com.innovix.domain.order.orderItems.OrderItemsDTO;
import br.com.innovix.domain.order.orderItems.OrderItemsService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemsDTO> getOrderItemsById(@PathVariable @NotBlank Long id) {
        OrderItemsDTO orderItem = orderItemsService.findOrderById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemsDTO> updateOrderItem(@PathVariable @NotBlank Long id, @Validated @RequestBody OrderItemsDTO orderItemsDTO) {
        OrderItemsDTO updatedOrderItem = orderItemsService.updateOrderItem(id, orderItemsDTO);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable @NotBlank Long id) {
        orderItemsService.deleteOrderItem(id);
        return ResponseEntity.ok().build();
    }
}