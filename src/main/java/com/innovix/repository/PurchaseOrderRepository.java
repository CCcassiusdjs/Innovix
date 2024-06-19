package com.innovix.repository;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByOrderStatus(String orderStatus);
    List<PurchaseOrder> findByCustomer(Person customer);
    List<PurchaseOrder> findByOrderLocalDateBetween(LocalDate startLocalDate, LocalDate endLocalDate);
    List<PurchaseOrder> findByProductId(Long productId);
}
