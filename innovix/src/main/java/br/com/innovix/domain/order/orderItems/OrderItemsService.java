package br.com.innovix.domain.order.orderItems;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.domain.product.product.ProductRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderItemsService(OrderItemsRepository orderItemsRepository, ProductRepository productRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<OrderItemsDTO> findAllOrders() {
        return orderItemsRepository.findAll().stream()
                .map(OrderItemsDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public OrderItemsDTO findOrderById(Long id) {
        OrderItemsEntity order = orderItemsRepository.findById(id)
                .orElseThrow(() -> new Exceptions.ItemInOrderNotFoundException("Item in order not found with ID: " + id));
        return OrderItemsDTO.fromEntity(order);
    }

    public OrderItemsDTO addItemInOrder(OrderItemsDTO orderItemsDTO) {
        OrderItemsEntity orderItemsEntity = orderItemsDTO.toEntity();
        return OrderItemsDTO.fromEntity(orderItemsRepository.save(orderItemsEntity));
    }

    public OrderItemsDTO updateOrderItem(Long id, OrderItemsDTO orderItemsDTO) {
        OrderItemsEntity orderItem = orderItemsRepository.findById(id)
                .orElseThrow(() -> new Exceptions.ItemInOrderNotFoundException("Item not found in order with ID: " + id));

        orderItem.setDiscount(orderItemsDTO.discount());
        orderItem.setQuantity(orderItemsDTO.quantity());
        // Atualizar relação com ProductEntity e OrderEntity, se necessário
        //orderItem.setProductByCodProd(productRepository.findById(orderItemsDTO.codProd()).orElseThrow());
        //orderItem.setOrder(orderRepository.findById(orderItemsDTO.codOrder()).orElseThrow(...));


        return OrderItemsDTO.fromEntity(orderItemsRepository.save(orderItem));
    }

    public void deleteOrderItem(Long id) {
        OrderItemsEntity orderItem = orderItemsRepository.findById(id)
                .orElseThrow(() -> new Exceptions.ItemInOrderNotFoundException("Item not found in order with ID: " + id));
        orderItemsRepository.delete(orderItem);
    }
}