package com.innovix.service;

import com.innovix.entity.Person;
import com.innovix.entity.PurchaseOrder;
import com.innovix.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    private PromotionService promotionService;

    @InjectMocks
    private OrderService orderService;

    private PurchaseOrder order1;
    private PurchaseOrder order2;
    private Person customer;

    @BeforeEach
    void setUp() {
        order1 = new PurchaseOrder();
        order1.setId(1L);
        order1.setOrderStatus("NEW");

        order2 = new PurchaseOrder();
        order2.setId(2L);
        order2.setOrderStatus("SHIPPED");

        customer = new Person();
        customer.setId(1L);
    }

    @Test
    void testListAll() {
        when(purchaseOrderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<PurchaseOrder> orders = orderService.listAll();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(purchaseOrderRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        doNothing().when(promotionService).applyPromotion(order1);
        when(purchaseOrderRepository.save(order1)).thenReturn(order1);

        PurchaseOrder savedOrder = orderService.save(order1);

        assertNotNull(savedOrder);
        assertEquals(1L, savedOrder.getId());
        verify(promotionService, times(1)).applyPromotion(order1);
        verify(purchaseOrderRepository, times(1)).save(order1);
    }

    @Test
    void testFindById() {
        when(purchaseOrderRepository.findById(1L)).thenReturn(Optional.of(order1));

        PurchaseOrder foundOrder = orderService.findById(1L);

        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
        verify(purchaseOrderRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByStatus() {
        when(purchaseOrderRepository.findByOrderStatus("NEW")).thenReturn(Arrays.asList(order1));

        List<PurchaseOrder> orders = orderService.findByStatus("NEW");

        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals("NEW", orders.get(0).getOrderStatus());
        verify(purchaseOrderRepository, times(1)).findByOrderStatus("NEW");
    }

    @Test
    void testFindByCustomer() {
        when(purchaseOrderRepository.findByCustomer(customer)).thenReturn(Arrays.asList(order1, order2));

        List<PurchaseOrder> orders = orderService.findByCustomer(customer);

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(purchaseOrderRepository, times(1)).findByCustomer(customer);
    }

    @Test
    void testFindByLocalDateRange() {
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        LocalDate endDate = LocalDate.of(2021, 12, 31);
        when(purchaseOrderRepository.findByOrderLocalDateBetween(startDate, endDate)).thenReturn(Arrays.asList(order1, order2));

        List<PurchaseOrder> orders = orderService.findByLocalDateRange(startDate, endDate);

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(purchaseOrderRepository, times(1)).findByOrderLocalDateBetween(startDate, endDate);
    }

    @Test
    void testFindByProductId() {
        when(purchaseOrderRepository.findByProductId(1L)).thenReturn(Arrays.asList(order1));

        List<PurchaseOrder> orders = orderService.findByProductId(1L);

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(purchaseOrderRepository, times(1)).findByProductId(1L);
    }

    @Test
    void testDelete() {
        doNothing().when(purchaseOrderRepository).deleteById(1L);

        orderService.delete(1L);

        verify(purchaseOrderRepository, times(1)).deleteById(1L);
    }
}
