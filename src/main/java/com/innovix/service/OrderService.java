package com.innovix.service;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Person;
import com.innovix.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PromotionService promotionService;

    @Autowired
    public OrderService(PurchaseOrderRepository purchaseOrderRepository, PromotionService promotionService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.promotionService = promotionService;
    }

    public List<PurchaseOrder> listAll() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        promotionService.applyPromotion(purchaseOrder);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    public List<PurchaseOrder> findByStatus(String status) {
        return purchaseOrderRepository.findByOrderStatus(status);
    }

    public List<PurchaseOrder> findByCustomer(Person customer) {
        return purchaseOrderRepository.findByCustomer(customer);
    }

    public List<PurchaseOrder> findByLocalDateRange(LocalDate startLocalDate, LocalDate endLocalDate) {
        return purchaseOrderRepository.findByOrderLocalDateBetween(startLocalDate, endLocalDate);
    }

    public List<PurchaseOrder> findByProductId(Long productId) {
        return purchaseOrderRepository.findByProductId(productId);
    }

    public void delete(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
