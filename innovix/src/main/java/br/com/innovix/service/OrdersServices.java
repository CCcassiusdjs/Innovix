package br.com.innovix.service;

import br.com.innovix.dto.OrdersDTOs;
import br.com.innovix.entity.OrdersEntities;
import br.com.innovix.exception.Exceptions;

import br.com.innovix.repository.OrdersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersServices {

    @Service
    public static class OrderService {

        @Autowired
        private OrdersRepositories.OrderRepository orderRepository;

        public List<OrdersDTOs.OrderDTO> findAllOrders() {
            return orderRepository.findAll().stream()
                    .map(OrdersDTOs.OrderDTO::fromEntity)
                    .collect(Collectors.toList());
        }

        public OrdersDTOs.OrderDTO findOrderById(Long id) {
            OrdersEntities.OrderEntity order = orderRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.OrderNotFoundException("Ordem não encontrada com o ID: " + id));
            return OrdersDTOs.OrderDTO.fromEntity(order);
        }

        public OrdersDTOs.OrderDTO addOrder(OrdersDTOs.OrderDTO orderDTO) {
            OrdersEntities.OrderEntity orderEntity = orderDTO.toEntity();
            return OrdersDTOs.OrderDTO.fromEntity(orderRepository.save(orderEntity));
        }

        public OrdersDTOs.OrderDTO updateOrder(Long id, OrdersDTOs.OrderDTO orderDTO) {
            OrdersEntities.OrderEntity existingOrder = orderRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.OrderNotFoundException("Ordem não encontrada com o ID: " + id));

            existingOrder.setCompanyName(orderDTO.companyName());
            existingOrder.setSender(orderDTO.sender());
            existingOrder.setRecipient(orderDTO.recipient());
            existingOrder.setShipCost(orderDTO.shipCost());
            existingOrder.setState(orderDTO.state());
            // Atualizar relação com CustomerEntity, se necessário

            return OrdersDTOs.OrderDTO.fromEntity(orderRepository.save(existingOrder));
        }

        public void deleteOrder(Long id) {
            OrdersEntities.OrderEntity order = orderRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.OrderNotFoundException("Ordem não encontrada com o ID: " + id));
            orderRepository.delete(order);
        }
    }

    @Service
    public static class OrderItemsService {

        @Autowired
        private OrdersRepositories.OrderItemsRepository orderItemsRepository;

        public OrdersDTOs.OrderItemsDTO updateOrderItem(Long id, OrdersDTOs.OrderItemsDTO orderItemsDTO) {
            OrdersEntities.OrderItemsEntity orderItem = orderItemsRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ItemInOrderNotFoundException("Item não encontrado na ordem de compra com ID: " + id));

            orderItem.setDiscount(orderItemsDTO.discount());
            orderItem.setQuantity(orderItemsDTO.quantity());
            // Atualizar relação com ProductEntity e OrderEntity, se necessário

            return OrdersDTOs.OrderItemsDTO.fromEntity(orderItemsRepository.save(orderItem));
        }

        public void deleteOrderItem(Long id) {
            OrdersEntities.OrderItemsEntity orderItem = orderItemsRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.ItemInOrderNotFoundException("Item não encontrado na ordem de compra com ID: " + id));
            orderItemsRepository.delete(orderItem);
        }
    }
}
