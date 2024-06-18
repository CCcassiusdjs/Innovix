package com.innovix.repository;

import com.innovix.entity.PurchaseOrder;
import com.innovix.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByOrderStatus(String orderStatus);
    List<PurchaseOrder> findByCustomer(Person customer);
    List<PurchaseOrder> findByOrderDateBetween(Date startDate, Date endDate);
    List<PurchaseOrder> findByProductId(Long productId);
}
