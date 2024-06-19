package com.innovix.controller;

import com.innovix.dto.OrderDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.OrderMapper;
import com.innovix.usecase.OrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderUseCase orderUseCase;

    @Autowired
    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @GetMapping
    public List<OrderDTO> listAll() {
        return orderUseCase.listAllOrders().stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public OrderDTO save(@RequestBody OrderDTO orderDTO) {
        return OrderMapper.INSTANCE.toDto(
                orderUseCase.createOrder(OrderMapper.INSTANCE.toEntity(orderDTO))
        );
    }

    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable Long id) {
        return OrderMapper.INSTANCE.toDto(orderUseCase.getOrderById(id));
    }

    @GetMapping("/status/{status}")
    public List<OrderDTO> listByStatus(@PathVariable String status) {
        return orderUseCase.listOrdersByStatus(status).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderDTO> listByCustomer(@PathVariable Long customerId) {
        return orderUseCase.listOrdersByCustomer(new Person(customerId)).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/date-range")
    public List<OrderDTO> listByLocalDateRange(@RequestParam LocalDate startLocalDate, @RequestParam LocalDate endLocalDate) {
        return orderUseCase.listOrdersByLocalDateRange(startLocalDate, endLocalDate).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{productId}")
    public List<OrderDTO> listByProductId(@PathVariable Long productId) {
        return orderUseCase.listOrdersByProductId(productId).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderUseCase.deleteOrder(id);
    }
}
