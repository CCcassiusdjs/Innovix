package com.innovix.repository;

import com.innovix.entity.Person;
import com.innovix.entity.Product;
import com.innovix.entity.ShoppingCart;
import com.innovix.repository.PersonRepository;
import com.innovix.repository.ProductRepository;
import com.innovix.repository.ShoppingCartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ShoppingCartRepositoryTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProductRepository productRepository;

    private Person customer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        customer = new Person();
        customer.setFullName("John Doe");
        customer = personRepository.save(customer);
    }

    @org.junit.jupiter.api.Test
    void tearDown() {
        shoppingCartRepository.deleteAll();
        personRepository.deleteAll();
        productRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByCustomer() {
        // Create a shopping cart for the customer
        Product product = new Product();
        product.setName("Product A");
        product.setPrice(10.0);
        product = productRepository.save(product);

        ShoppingCart cart = new ShoppingCart();
        cart.setCustomer(customer);
        cart.setProductName("Product A");
        cart.setProductPrice(10.0);
        cart.setProductQuantity(1);
        cart.setProductSubtotal(10.0);
        cart.setSubtotal(10.0);
        cart.setProduct(product);

        shoppingCartRepository.save(cart);

        // Test the findByCustomer method
        List<ShoppingCart> carts = shoppingCartRepository.findByCustomer(customer);
        assertNotNull(carts);
        assertEquals(1, carts.size());
        assertEquals("Product A", carts.get(0).getProductName());
    }
}
