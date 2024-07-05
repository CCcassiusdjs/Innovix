package com.innovix.service;

import com.innovix.entity.PaymentMethod;
import com.innovix.repository.PaymentMethodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodServiceTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @InjectMocks
    private PaymentMethodService paymentMethodService;

    private PaymentMethod paymentMethod1;
    private PaymentMethod paymentMethod2;

    @BeforeEach
    void setUp() {
        paymentMethod1 = new PaymentMethod();
        paymentMethod1.setId(1L);
        paymentMethod1.setCardNumber("1234567890123456");
        paymentMethod1.setPaymentType("Credit Card");

        paymentMethod2 = new PaymentMethod();
        paymentMethod2.setId(2L);
        paymentMethod2.setCardNumber("6543210987654321");
        paymentMethod2.setPaymentType("Debit Card");
    }

    @Test
    void testListAll() {
        when(paymentMethodRepository.findAll()).thenReturn(Arrays.asList(paymentMethod1, paymentMethod2));

        List<PaymentMethod> paymentMethods = paymentMethodService.listAll();

        assertNotNull(paymentMethods);
        assertEquals(2, paymentMethods.size());
        verify(paymentMethodRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(paymentMethodRepository.save(paymentMethod1)).thenReturn(paymentMethod1);

        PaymentMethod savedPaymentMethod = paymentMethodService.save(paymentMethod1);

        assertNotNull(savedPaymentMethod);
        assertEquals(1L, savedPaymentMethod.getId());
        verify(paymentMethodRepository, times(1)).save(paymentMethod1);
    }

    @Test
    void testFindById() {
        when(paymentMethodRepository.findById(1L)).thenReturn(Optional.of(paymentMethod1));

        PaymentMethod foundPaymentMethod = paymentMethodService.findById(1L);

        assertNotNull(foundPaymentMethod);
        assertEquals(1L, foundPaymentMethod.getId());
        verify(paymentMethodRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByPersonId() {
        when(paymentMethodRepository.findByPersonId(1L)).thenReturn(Arrays.asList(paymentMethod1, paymentMethod2));

        List<PaymentMethod> paymentMethods = paymentMethodService.findByPersonId(1L);

        assertNotNull(paymentMethods);
        assertEquals(2, paymentMethods.size());
        verify(paymentMethodRepository, times(1)).findByPersonId(1L);
    }

    @Test
    void testFindByPaymentType() {
        when(paymentMethodRepository.findByPaymentType("Credit Card")).thenReturn(Arrays.asList(paymentMethod1));

        List<PaymentMethod> paymentMethods = paymentMethodService.findByPaymentType("Credit Card");

        assertNotNull(paymentMethods);
        assertEquals(1, paymentMethods.size());
        assertEquals("Credit Card", paymentMethods.get(0).getPaymentType());
        verify(paymentMethodRepository, times(1)).findByPaymentType("Credit Card");
    }

    @Test
    void testFindByCardNumber() {
        when(paymentMethodRepository.findByCardNumber("1234567890123456")).thenReturn(paymentMethod1);

        PaymentMethod foundPaymentMethod = paymentMethodService.findByCardNumber("1234567890123456");

        assertNotNull(foundPaymentMethod);
        assertEquals("1234567890123456", foundPaymentMethod.getCardNumber());
        verify(paymentMethodRepository, times(1)).findByCardNumber("1234567890123456");
    }

    @Test
    void testDelete() {
        doNothing().when(paymentMethodRepository).deleteById(1L);

        paymentMethodService.delete(1L);

        verify(paymentMethodRepository, times(1)).deleteById(1L);
    }
}
