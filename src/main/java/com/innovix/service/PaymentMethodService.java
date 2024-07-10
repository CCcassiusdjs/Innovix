package com.innovix.service;

import com.innovix.entity.PaymentMethod;
import com.innovix.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing payment methods.
 * <p>
 * This class provides methods for CRUD operations on PaymentMethod entities.
 * </p>
 */
@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * Lists all payment methods.
     *
     * @return A list of all payment methods.
     */
    public List<PaymentMethod> listAll() {
        return paymentMethodRepository.findAll();
    }

    /**
     * Saves a new payment method or updates an existing one.
     *
     * @param paymentMethod The payment method to save.
     * @return The saved payment method.
     */
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    /**
     * Finds a payment method by its ID.
     *
     * @param id The ID of the payment method.
     * @return The payment method with the specified ID, or null if not found.
     */
    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

    /**
     * Finds payment methods by person ID.
     *
     * @param personId The ID of the person.
     * @return A list of payment methods associated with the specified person ID.
     */
    public List<PaymentMethod> findByPersonId(Long personId) {
        return paymentMethodRepository.findByPersonId(personId);
    }

    /**
     * Finds payment methods by payment type.
     *
     * @param paymentType The type of payment.
     * @return A list of payment methods with the specified payment type.
     */
    public List<PaymentMethod> findByPaymentType(String paymentType) {
        return paymentMethodRepository.findByPaymentType(paymentType);
    }

    /**
     * Finds a payment method by card number.
     *
     * @param cardNumber The card number.
     * @return The payment method with the specified card number.
     */
    public PaymentMethod findByCardNumber(String cardNumber) {
        return paymentMethodRepository.findByCardNumber(cardNumber);
    }

    /**
     * Deletes a payment method by its ID.
     *
     * @param id The ID of the payment method to delete.
     */
    public void delete(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
