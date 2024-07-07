package com.innovix.service;

import com.innovix.entity.Product;
import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Promotion;
import com.innovix.repository.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionService promotionService;

    private Promotion promotion1;
    private Promotion promotion2;
    private Product product;

    @BeforeEach
    void setUp() {
        promotion1 = new Promotion();
        promotion1.setId(1L);
        promotion1.setDescription("buy2take3");
        promotion1.setRequiredQuantity(2);
        promotion1.setFreeQuantity(1);

        promotion2 = new Promotion();
        promotion2.setId(2L);
        promotion2.setDescription("25% Off");
        promotion2.setRequiredQuantity(0);

        product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPromotion(promotion1);
    }

    @Test
    void testListAll() {
        when(promotionRepository.findAll()).thenReturn(Arrays.asList(promotion1, promotion2));

        List<Promotion> promotions = promotionService.listAll();

        assertNotNull(promotions);
        assertEquals(2, promotions.size());
        assertEquals("buy2take3", promotions.get(0).getDescription());
        assertEquals("25% Off", promotions.get(1).getDescription());

        verify(promotionRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(promotionRepository.save(promotion1)).thenReturn(promotion1);

        Promotion savedPromotion = promotionService.save(promotion1);

        assertNotNull(savedPromotion);
        assertEquals(1L, savedPromotion.getId());
        assertEquals("buy2take3", savedPromotion.getDescription());

        verify(promotionRepository, times(1)).save(promotion1);
    }

    @Test
    void testFindById() {
        when(promotionRepository.findById(1L)).thenReturn(Optional.of(promotion1));

        Promotion foundPromotion = promotionService.findById(1L);

        assertNotNull(foundPromotion);
        assertEquals(1L, foundPromotion.getId());
        assertEquals("buy2take3", foundPromotion.getDescription());

        verify(promotionRepository, times(1)).findById(1L);
    }

    @Test
    void testFindBySeason() {
        String season = "Summer";
        when(promotionRepository.findBySeason(season)).thenReturn(Arrays.asList(promotion1));

        List<Promotion> promotions = promotionService.findBySeason(season);

        assertNotNull(promotions);
        assertEquals(1, promotions.size());
        assertEquals("buy2take3", promotions.get(0).getDescription());

        verify(promotionRepository, times(1)).findBySeason(season);
    }

    @Test
    void testFindByInitLocalDateBefore() {
        LocalDate date = LocalDate.of(2024, 7, 1);
        when(promotionRepository.findByInitLocalDateBefore(date)).thenReturn(Arrays.asList(promotion1));

        List<Promotion> promotions = promotionService.findByInitLocalDateBefore(date);

        assertNotNull(promotions);
        assertEquals(1, promotions.size());
        assertEquals("buy2take3", promotions.get(0).getDescription());

        verify(promotionRepository, times(1)).findByInitLocalDateBefore(date);
    }

    @Test
    void testFindByEndLocalDateAfter() {
        LocalDate date = LocalDate.of(2024, 12, 31);
        when(promotionRepository.findByEndLocalDateAfter(date)).thenReturn(Arrays.asList(promotion1));

        List<Promotion> promotions = promotionService.findByEndLocalDateAfter(date);

        assertNotNull(promotions);
        assertEquals(1, promotions.size());
        assertEquals("buy2take3", promotions.get(0).getDescription());

        verify(promotionRepository, times(1)).findByEndLocalDateAfter(date);
    }

    @Test
    void testFindByEmployeeId() {
        Long employeeId = 1L;
        when(promotionRepository.findByEmployeeId(employeeId)).thenReturn(Arrays.asList(promotion1));

        List<Promotion> promotions = promotionService.findByEmployeeId(employeeId);

        assertNotNull(promotions);
        assertEquals(1, promotions.size());
        assertEquals("buy2take3", promotions.get(0).getDescription());

        verify(promotionRepository, times(1)).findByEmployeeId(employeeId);
    }

    @Test
    void testDelete() {
        doNothing().when(promotionRepository).deleteById(1L);

        promotionService.delete(1L);

        verify(promotionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testApplyPromotion() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1L);
        purchaseOrder.setProduct(product);
        purchaseOrder.setProductQuantity(4);

        promotionService.applyPromotion(purchaseOrder);

        assertEquals(2, purchaseOrder.getFreeQuantity());
        assertEquals(new BigDecimal("100.0"), purchaseOrder.getProductSubtotal());
    }
}
