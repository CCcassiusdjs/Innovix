package br.com.innovix.controller;

import br.com.innovix.dto.OrderDTO;
import br.com.innovix.entity.OrderEntity;
import br.com.innovix.exception.OrderNotFoundException;
import br.com.innovix.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Ordem não encontrada com o ID: " + id));
        return OrderDTO.fromEntity(order);
    }

    @PostMapping
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCompanyName(orderDTO.companyName());
        orderEntity.setSender(orderDTO.sender());
        orderEntity.setRecipient(orderDTO.recipient());
        orderEntity.setShipCost(orderDTO.shipCost());
        orderEntity.setState(orderDTO.state());
        // Gerenciar relação com CustomerEntity aqui

        OrderEntity savedOrder = orderRepository.save(orderEntity);
        return OrderDTO.fromEntity(savedOrder);
    }

    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        OrderEntity existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Ordem não encontrada com o ID: " + id));

        existingOrder.setCompanyName(orderDTO.companyName());
        existingOrder.setSender(orderDTO.sender());
        existingOrder.setRecipient(orderDTO.recipient());
        existingOrder.setShipCost(orderDTO.shipCost());
        existingOrder.setState(orderDTO.state());
        // Atualizar relação com CustomerEntity, se necessário

        OrderEntity updatedOrder = orderRepository.save(existingOrder);
        return OrderDTO.fromEntity(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Ordem não encontrada com o ID: " + id));
        orderRepository.delete(order);
        return ResponseEntity.ok().build();
    }
}
