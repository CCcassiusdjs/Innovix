package com.innovix.service;

import com.innovix.entity.Product;
import com.innovix.entity.Promotion;
import com.innovix.entity.PurchaseOrder;
import com.innovix.repository.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionService promotionService;

    private Promotion promotion;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        promotion = new Promotion();
        promotion.setId(1L);
        promotion.setDescription("buy2take3");
        promotion.setSeason("Summer");
        promotion.setInitLocalDate(LocalDate.of(2023, 1, 1));
        promotion.setEndLocalDate(LocalDate.of(2023, 12, 31));
        promotion.setRequiredQuantity(2);
        promotion.setFreeQuantity(1);

        product = new Product();
        product.setPromotion(promotion);
    }

    @Test
    void listAll() {
        when(promotionRepository.findAll()).thenReturn(Collections.singletonList(promotion));

        List<Promotion> promotions = promotionService.listAll();

        assertEquals(1, promotions.size());
        verify(promotionRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(promotionRepository.save(any(Promotion.class))).thenReturn(promotion);

        Promotion savedPromotion = promotionService.save(promotion);

        assertNotNull(savedPromotion);
        assertEquals(promotion.getId(), savedPromotion.getId());
        verify(promotionRepository, times(1)).save(promotion);
    }

    @Test
    void findById() {
        when(promotionRepository.findById(1L)).thenReturn(Optional.of(promotion));

        Promotion foundPromotion = promotionService.findById(1L);

        assertNotNull(foundPromotion);
        assertEquals(promotion.getId(), foundPromotion.getId());
        verify(promotionRepository, times(1)).findById(1L);
    }

    @Test
    void findBySeason() {
        when(promotionRepository.findBySeason("Summer")).thenReturn(Collections.singletonList(promotion));

        List<Promotion> promotions = promotionService.findBySeason("Summer");

        assertEquals(1, promotions.size());
        verify(promotionRepository, times(1)).findBySeason("Summer");
    }

    @Test
    void findByInitLocalDateBefore() {
        when(promotionRepository.findByInitLocalDateBefore(LocalDate.of(2023, 6, 1)))
                .thenReturn(Collections.singletonList(promotion));

        List<Promotion> promotions = promotionService.findByInitLocalDateBefore(LocalDate.of(2023, 6, 1));

        assertEquals(1, promotions.size());
        verify(promotionRepository, times(1)).findByInitLocalDateBefore(LocalDate.of(2023, 6, 1));
    }

    @Test
    void findByEndLocalDateAfter() {
        when(promotionRepository.findByEndLocalDateAfter(LocalDate.of(2023, 6, 1)))
                .thenReturn(Collections.singletonList(promotion));

        List<Promotion> promotions = promotionService.findByEndLocalDateAfter(LocalDate.of(2023, 6, 1));

        assertEquals(1, promotions.size());
        verify(promotionRepository, times(1)).findByEndLocalDateAfter(LocalDate.of(2023, 6, 1));
    }

    @Test
    void findByEmployeeId() {
        when(promotionRepository.findByEmployeeId(1L)).thenReturn(Collections.singletonList(promotion));

        List<Promotion> promotions = promotionService.findByEmployeeId(1L);

        assertEquals(1, promotions.size());
        verify(promotionRepository, times(1)).findByEmployeeId(1L);
    }

    @Test
    void delete() {
        doNothing().when(promotionRepository).deleteById(1L);

        promotionService.delete(1L);

        verify(promotionRepository, times(1)).deleteById(1L);
    }

    @Test
    void applyPromotion() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductQuantity(6);
        purchaseOrder.setProductPrice(BigDecimal.valueOf(10.00));
        purchaseOrder.setProductSubtotal(BigDecimal.valueOf(60.00));
        purchaseOrder.setProduct(product);

        promotionService.applyPromotion(purchaseOrder);

        assertEquals(3, purchaseOrder.getFreeQuantity());
        assertEquals(BigDecimal.valueOf(30.00), purchaseOrder.getProductSubtotal());
    }

    @Test
    void applyPromotionWithNullPromotion() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductQuantity(6);
        purchaseOrder.setProductPrice(BigDecimal.valueOf(10.00));
        purchaseOrder.setProductSubtotal(BigDecimal.valueOf(60.00));
        Product productWithoutPromotion = new Product();
        productWithoutPromotion.setPromotion(null);
        purchaseOrder.setProduct(productWithoutPromotion);

        promotionService.applyPromotion(purchaseOrder);

        assertEquals(0, purchaseOrder.getFreeQuantity());
        assertEquals(BigDecimal.valueOf(60.00), purchaseOrder.getProductSubtotal());
    }

    @Test
    void applyPromotionWithDifferentDescription() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductQuantity(6);
        purchaseOrder.setProductPrice(BigDecimal.valueOf(10.00));
        purchaseOrder.setProductSubtotal(BigDecimal.valueOf(60.00));
        Promotion differentPromotion = new Promotion();
        differentPromotion.setDescription("differentPromotion");
        Product productWithDifferentPromotion = new Product();
        productWithDifferentPromotion.setPromotion(differentPromotion);
        purchaseOrder.setProduct(productWithDifferentPromotion);

        promotionService.applyPromotion(purchaseOrder);

        assertEquals(0, purchaseOrder.getFreeQuantity());
        assertEquals(BigDecimal.valueOf(60.00), purchaseOrder.getProductSubtotal());
    }

    @Test
    void applyBuy2Take3Promotion() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductQuantity(6);
        purchaseOrder.setProductPrice(BigDecimal.valueOf(10.00));
        purchaseOrder.setProductSubtotal(BigDecimal.valueOf(60.00));

        promotionService.applyBuy2Take3Promotion(purchaseOrder, promotion);

        assertEquals(3, purchaseOrder.getFreeQuantity());
        assertEquals(BigDecimal.valueOf(30.00), purchaseOrder.getProductSubtotal());
    }
}
