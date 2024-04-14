package br.com.innovix.controller;

import br.com.innovix.dto.OrdersDTOs;
import br.com.innovix.service.OrdersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public class OrdersControllers {
    @RestController
    @RequestMapping("/orders")
    public static class OrderController {
        
        private final OrdersServices.OrderService orderService;

        @Autowired
        public OrderController(OrdersServices.OrderService orderService) {
            this.orderService = orderService;
        }

        @GetMapping
        public List<OrdersDTOs.OrderDTO> getAllOrders() {
            return orderService.findAllOrders();
        }

        
        @GetMapping("/{id}")
        public OrdersDTOs.OrderDTO getOrderById(@PathVariable Long id) {
            return orderService.findOrderById(id);
        }

        
        @PostMapping
        public OrdersDTOs.OrderDTO addOrder(@RequestBody OrdersDTOs.OrderDTO orderDTO) {
            return orderService.addOrder(orderDTO);
        }

        
        @PutMapping("/{id}")
        public OrdersDTOs.OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrdersDTOs.OrderDTO orderDTO) {
            return orderService.updateOrder(id, orderDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        }
    }

    @RestController
    @RequestMapping("/orderItems")
    public static class OrderItemsController {
        
        private final OrdersServices.OrderItemsService orderItemsService;

        @Autowired
        public OrderItemsController(OrdersServices.OrderItemsService orderItemsService) {
            this.orderItemsService = orderItemsService;
        }

        
        @PutMapping("/{id}")
        public OrdersDTOs.OrderItemsDTO updateOrderItem(@PathVariable Long id, @RequestBody OrdersDTOs.OrderItemsDTO orderDTO) {
            return orderItemsService.updateOrderItem(id, orderDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
            orderItemsService.deleteOrderItem(id);
            return ResponseEntity.ok().build();
        }
    }
}