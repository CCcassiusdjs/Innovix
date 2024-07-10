package com.innovix.repository;

import com.innovix.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for PaymentMethod entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for PaymentMethod entities.
 * </p>
 */
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    /**
     * Finds payment methods by person ID.
     *
     * @param personId The ID of the person.
     * @return A list of payment methods associated with the specified person ID.
     */
    List<PaymentMethod> findByPersonId(Long personId);

    /**
     * Finds payment methods by payment type.
     *
     * @param paymentType The type of payment.
     * @return A list of payment methods with the specified payment type.
     */
    List<PaymentMethod> findByPaymentType(String paymentType);

    /**
     * Finds a payment method by card number.
     *
     * @param cardNumber The card number.
     * @return The payment method with the specified card number.
     */
    PaymentMethod findByCardNumber(String cardNumber);
}
