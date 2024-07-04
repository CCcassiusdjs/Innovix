package com.innovix.mapper;

import com.innovix.dto.ShoppingCartDTO;
import com.innovix.entity.Person;
import com.innovix.entity.ShoppingCart;
import com.innovix.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartMapperTest {

    private final ShoppingCartMapper shoppingCartMapper = ShoppingCartMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        Person customer = new Person();
        customer.setId(2L);
        shoppingCart.setCustomer(customer);
        Product product = new Product();
        product.setId(3L);
        shoppingCart.setProduct(product);

        // Act
        ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toDto(shoppingCart);

        // Assert
        assertNotNull(shoppingCartDTO);
        assertEquals(1L, shoppingCartDTO.getShoppingCartId());
        assertEquals(2L, shoppingCartDTO.getCustomerId());
        assertEquals(3L, shoppingCartDTO.getProductId());
    }

    @Test
    void toEntity() {
        // Arrange
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setShoppingCartId(1L);
        shoppingCartDTO.setCustomerId(2L);
        shoppingCartDTO.setProductId(3L);

        // Act
        ShoppingCart shoppingCart = shoppingCartMapper.toEntity(shoppingCartDTO);

        // Assert
        assertNotNull(shoppingCart);
        assertEquals(1L, shoppingCart.getId());
        assertNotNull(shoppingCart.getCustomer());
        assertEquals(2L, shoppingCart.getCustomer().getId());
        assertNotNull(shoppingCart.getProduct());
        assertEquals(3L, shoppingCart.getProduct().getId());
    }
}
