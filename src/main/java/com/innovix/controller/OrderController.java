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

/**
 * REST controller for managing orders.
 */
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

    /**
     * Lists all orders.
     *
     * @return A list of all orders.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listAll() {
        return employeeUseCase.listAllOrders().stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new order.
     *
     * @param orderDTO The order data transfer object.
     * @return The saved order data transfer object.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OrderDTO save(@RequestBody OrderDTO orderDTO) {
        return OrderMapper.INSTANCE.toDto(
                customerUseCase.createOrder(OrderMapper.INSTANCE.toEntity(orderDTO))
        );
    }

    /**
     * Gets an order by ID.
     *
     * @param id The ID of the order.
     * @return The order data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public OrderDTO getById(@PathVariable Long id) {
        return OrderMapper.INSTANCE.toDto(employeeUseCase.getOrderById(id));
    }

    /**
     * Lists orders by status.
     *
     * @param status The order status.
     * @return A list of orders with the specified status.
     */
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listByStatus(@PathVariable String status) {
        return employeeUseCase.listOrdersByStatus(status).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists orders by customer ID.
     *
     * @param customerId The ID of the customer.
     * @return A list of orders associated with the specified customer.
     */
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<OrderDTO> listByCustomer(@PathVariable Long customerId) {
        return customerUseCase.listOrdersByCustomer(new Person(customerId)).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists orders by date range.
     *
     * @param startLocalDate The start date of the range.
     * @param endLocalDate   The end date of the range.
     * @return A list of orders within the specified date range.
     */
    @GetMapping("/date-range")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listByLocalDateRange(@RequestParam LocalDate startLocalDate, @RequestParam LocalDate endLocalDate) {
        return employeeUseCase.listOrdersByLocalDateRange(startLocalDate, endLocalDate).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists orders by product ID.
     *
     * @param productId The ID of the product.
     * @return A list of orders containing the specified product.
     */
    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<OrderDTO> listByProductId(@PathVariable Long productId) {
        return employeeUseCase.listOrdersByProductId(productId).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes an order by ID.
     *
     * @param id The ID of the order to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteOrder(id);
    }
}
