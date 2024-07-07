package com.innovix.mapper;

import com.innovix.dto.OrderDTO;
import com.innovix.entity.Address;
import com.innovix.entity.Person;
import com.innovix.entity.Product;
import com.innovix.entity.PurchaseOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        Person customer = new Person();
        customer.setId(1L);

        Address addressOrigin = new Address();
        addressOrigin.setId(1L);

        Address addressDestination = new Address();
        addressDestination.setId(2L);

        Product product = new Product();
        product.setId(1L);

        PurchaseOrder order = new PurchaseOrder();
        order.setId(1L);
        order.setCustomer(customer);
        order.setAddressOrigin(addressOrigin);
        order.setAddressDestination(addressDestination);
        order.setProduct(product);

        // Act
        OrderDTO orderDTO = orderMapper.toDto(order);

        // Assert
        assertNotNull(orderDTO);
        assertEquals(1L, orderDTO.getOrderId());
        assertEquals(1L, orderDTO.getCustomerId());
        assertEquals(1L, orderDTO.getAddressOriginId());
        assertEquals(2L, orderDTO.getAddressDestinationId());
        assertEquals(1L, orderDTO.getProductId());
    }

    @Test
    void toEntity() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1L);
        orderDTO.setCustomerId(1L);
        orderDTO.setAddressOriginId(1L);
        orderDTO.setAddressDestinationId(2L);
        orderDTO.setProductId(1L);

        // Act
        PurchaseOrder order = orderMapper.toEntity(orderDTO);

        // Assert
        assertNotNull(order);
        assertEquals(1L, order.getId());
        assertEquals(1L, order.getCustomer().getId());
        assertEquals(1L, order.getAddressOrigin().getId());
        assertEquals(2L, order.getAddressDestination().getId());
        assertEquals(1L, order.getProduct().getId());
    }
}
