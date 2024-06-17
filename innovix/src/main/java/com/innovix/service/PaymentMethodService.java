package com.innovix.service;

import com.innovix.entity.PaymentMethod;
import com.innovix.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage operations related to {@link PaymentMethod}.
 */
@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param paymentMethodRepository the payment method repository.
     */
    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * Lists all payment methods.
     *
     * @return a list of payment methods.
     */
    public List<PaymentMethod> listAll() {
        return paymentMethodRepository.findAll();
    }

    /**
     * Saves a new payment method.
     *
     * @param paymentMethod the payment method to save.
     * @return the saved payment method.
     */
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    /**
     * Finds a payment method by ID.
     *
     * @param id the ID of the payment method.
     * @return the found payment method, or {@code null} if not found.
     */
    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

/**
 * Finds payment methods by person ID.
 *
 * @param personId the ID of the
 * @return a list of payment methods associated with the person.
 */
public List<PaymentMethod> findByPersonId(Long personId) {
    return paymentMethodRepository.findByPersonId(personId);
}

    /**
     * Finds payment methods by payment type.
     *
     * @param paymentType the type of the payment method.
     * @return a list of payment methods with the specified type.
     */
    public List<PaymentMethod> findByPaymentType(String paymentType) {
        return paymentMethodRepository.findByPaymentType(paymentType);
    }

    /**
     * Finds a payment method by card number.
     *
     * @param cardNumber the card number of the payment method.
     * @return the found payment method, or {@code null} if not found.
     */
    public PaymentMethod findByCardNumber(String cardNumber) {
        return paymentMethodRepository.findByCardNumber(cardNumber);
    }

    /**
     * Deletes a payment method by ID.
     *
     * @param id the ID of the payment method to delete.
     */
    public void delete(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}