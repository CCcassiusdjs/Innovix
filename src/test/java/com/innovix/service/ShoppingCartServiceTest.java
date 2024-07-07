package com.innovix.service;

import com.innovix.entity.Person;
import com.innovix.entity.ShoppingCart;
import com.innovix.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ShoppingCart cart1;
    private ShoppingCart cart2;
    private Person customer;

    @BeforeEach
    void setUp() {
        customer = new Person();
        customer.setId(1L);
        customer.setEmail("customer@example.com");

        cart1 = new ShoppingCart();
        cart1.setId(1L);
        cart1.setCustomer(customer);

        cart2 = new ShoppingCart();
        cart2.setId(2L);
        cart2.setCustomer(customer);
    }

    @Test
    void testListAll() {
        when(shoppingCartRepository.findAll()).thenReturn(Arrays.asList(cart1, cart2));

        List<ShoppingCart> carts = shoppingCartService.listAll();

        assertEquals(2, carts.size());
        verify(shoppingCartRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(cart1);

        ShoppingCart savedCart = shoppingCartService.save(cart1);

        assertEquals(1L, savedCart.getId());
        verify(shoppingCartRepository, times(1)).save(cart1);
    }

    @Test
    void testFindById() {
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(cart1));

        ShoppingCart foundCart = shoppingCartService.findById(1L);

        assertEquals(1L, foundCart.getId());
        verify(shoppingCartRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByCustomer() {
        when(shoppingCartRepository.findByCustomer(customer)).thenReturn(Arrays.asList(cart1, cart2));

        List<ShoppingCart> carts = shoppingCartService.findByCustomer(customer);

        assertEquals(2, carts.size());
        verify(shoppingCartRepository, times(1)).findByCustomer(customer);
    }

    @Test
    void testDelete() {
        doNothing().when(shoppingCartRepository).deleteById(1L);

        shoppingCartService.delete(1L);

        verify(shoppingCartRepository, times(1)).deleteById(1L);
    }
}
