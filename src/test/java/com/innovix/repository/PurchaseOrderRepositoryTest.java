package com.innovix.repository;

import com.innovix.entity.Address;
import com.innovix.entity.Person;
import com.innovix.entity.Product;
import com.innovix.entity.PurchaseOrder;
import com.innovix.repository.AddressRepository;
import com.innovix.repository.PersonRepository;
import com.innovix.repository.ProductRepository;
import com.innovix.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PurchaseOrderRepositoryTest {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    private Person customer;
    private Address address;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        customer = new Person();
        customer.setName("John Doe");
        customer = personRepository.save(customer);

        address = new Address();
        address.setCity("City A");
        address.setState("State A");
        address.setCountry("Country A");
        address = addressRepository.save(address);
    }

    @org.junit.jupiter.api.Test
    void tearDown() {
        purchaseOrderRepository.deleteAll();
        personRepository.deleteAll();
        addressRepository.deleteAll();
        productRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByOrderStatus() {
        PurchaseOrder order = createSamplePurchaseOrder();
        order.setOrderStatus("Pending");
        purchaseOrderRepository.save(order);

        List<PurchaseOrder> foundOrders = purchaseOrderRepository.findByOrderStatus("Pending");
        assertNotNull(foundOrders);
        assertEquals(1, foundOrders.size());
        assertEquals("Pending", foundOrders.get(0).getOrderStatus());
    }

    @org.junit.jupiter.api.Test
    void findByCustomer() {
        PurchaseOrder order = createSamplePurchaseOrder();
        purchaseOrderRepository.save(order);

        List<PurchaseOrder> foundOrders = purchaseOrderRepository.findByCustomer(customer);
        assertNotNull(foundOrders);
        assertEquals(1, foundOrders.size());
        assertEquals(customer.getId(), foundOrders.get(0).getCustomer().getId());
    }

    @org.junit.jupiter.api.Test
    void findByOrderLocalDateBetween() {
        PurchaseOrder order1 = createSamplePurchaseOrder();
        order1.setOrderLocalDate(LocalDate.of(2024, 6, 1));
        purchaseOrderRepository.save(order1);

        PurchaseOrder order2 = createSamplePurchaseOrder();
        order2.setOrderLocalDate(LocalDate.of(2024, 6, 15));
        purchaseOrderRepository.save(order2);

        List<PurchaseOrder> foundOrders = purchaseOrderRepository.findByOrderLocalDateBetween(
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 10)
        );
        assertNotNull(foundOrders);
        assertEquals(1, foundOrders.size());
        assertEquals(order1.getId(), foundOrders.get(0).getId());
    }

    @org.junit.jupiter.api.Test
    void findByProductId() {
        Product product = new Product();
        product.setProductName("Product A");
        product.setProductPrice(new BigDecimal("100.00"));
        product = productRepository.save(product);

        PurchaseOrder order = createSamplePurchaseOrder();
        order.setProduct(product);
        purchaseOrderRepository.save(order);

        List<PurchaseOrder> foundOrders = purchaseOrderRepository.findByProductId(product.getId());
        assertNotNull(foundOrders);
        assertEquals(1, foundOrders.size());
        assertEquals(product.getId(), foundOrders.get(0).getProduct().getId());
    }

    private PurchaseOrder createSamplePurchaseOrder() {
        PurchaseOrder order = new PurchaseOrder();
        order.setOrderLocalDate(LocalDate.now());
        order.setOrderStatus("Completed");
        order.setCustomer(customer);
        order.setAddressOrigin(address);
        order.setAddressDestination(address);
        order.setProductName("Sample Product");
        order.setProductPrice(new BigDecimal("50.00"));
        order.setProductQuantity(1);
        order.setProductSubtotal(new BigDecimal("50.00"));
        order.setProduct(new Product());
        return order;
    }
}
