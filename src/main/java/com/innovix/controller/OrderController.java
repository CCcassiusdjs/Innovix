package com.innovix.controller;

import com.innovix.dto.OrderDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.OrderMapper;
import com.innovix.usecase.CustomerUseCase;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CustomerUseCase customerUseCase;
    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public OrderController(CustomerUseCase customerUseCase, EmployeeUseCase employeeUseCase) {
        this.customerUseCase = customerUseCase;
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listAll() {
        return employeeUseCase.listAllOrders().stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OrderDTO save(@RequestBody OrderDTO orderDTO) {
        return OrderMapper.INSTANCE.toDto(
                customerUseCase.createOrder(OrderMapper.INSTANCE.toEntity(orderDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public OrderDTO getById(@PathVariable Long id) {
        return OrderMapper.INSTANCE.toDto(employeeUseCase.getOrderById(id));
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listByStatus(@PathVariable String status) {
        return employeeUseCase.listOrdersByStatus(status).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<OrderDTO> listByCustomer(@PathVariable Long customerId) {
        return customerUseCase.listOrdersByCustomer(new Person(customerId)).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/date-range")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listByLocalDateRange(@RequestParam LocalDate startLocalDate, @RequestParam LocalDate endLocalDate) {
        return employeeUseCase.listOrdersByLocalDateRange(startLocalDate, endLocalDate).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listByProductId(@PathVariable Long productId) {
        return employeeUseCase.listOrdersByProductId(productId).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteOrder(id);
    }
}
