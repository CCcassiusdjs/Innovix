package br.com.innovix.controller;

import br.com.innovix.dto.OrderItemsDTO;
import br.com.innovix.entity.OrderItemsEntity;
import br.com.innovix.exception.ItemInOrderNotFoundException;
import br.com.innovix.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderItemsController(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    @PutMapping("/{id}")
    public OrderItemsDTO updateOrderItem(@PathVariable Long id, @RequestBody OrderItemsDTO orderItemsDTO) {
        OrderItemsEntity orderItem = orderItemsRepository.findById(id)
                .orElseThrow(() -> new ItemInOrderNotFoundException("Item não encontrado na ordem de compra com ID: " + id));

        orderItem.setDiscount(orderItemsDTO.discount());
        orderItem.setQuantity(orderItemsDTO.quantity());
        // Atualize a relação com ProductEntity e OrderEntity, se necessário

        OrderItemsEntity updatedOrderItem = orderItemsRepository.save(orderItem);
        return OrderItemsDTO.fromEntity(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
        OrderItemsEntity orderItem = orderItemsRepository.findById(id)
                .orElseThrow(() -> new ItemInOrderNotFoundException("Item não encontrado na ordem de compra com ID: " + id));
        orderItemsRepository.delete(orderItem);
        return ResponseEntity.ok().build();
    }
}
