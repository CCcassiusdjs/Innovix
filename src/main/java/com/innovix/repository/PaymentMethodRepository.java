package com.innovix.repository;

import com.innovix.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findByPersonId(Long personId);
    List<PaymentMethod> findByPaymentType(String paymentType);
    PaymentMethod findByCardNumber(String cardNumber);
}
