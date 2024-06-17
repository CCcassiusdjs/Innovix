package com.innovix.usecase;

import com.innovix.entity.PaymentMethod;
import com.innovix.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for managing operations related to {@link PaymentMethod}.
 */
@Component
public class PaymentMethodUseCase {

    private final PaymentMethodService paymentMethodService;

    /**
     * Constructor for dependency injection.
     *
     * @param paymentMethodService the payment method service.
     */
    @Autowired
    public PaymentMethodUseCase(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    /**
     * Lists all payment methods.
     *
     * @return a list of payment methods.
     */
    public List<PaymentMethod> listAllPaymentMethods() {
        return paymentMethodService.listAll();
    }

    /**
     * Creates a new payment method.
     *
     * @param paymentMethod the payment method to create.
     * @return the created payment method.
     */
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
    }

    /**
     * Gets a payment method by ID.
     *
     * @param id the ID of the payment method.
     * @return the found payment method, or {@code null} if not found.
     */
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodService.findById(id);
    }

    /**
     * Lists payment methods by person ID.
     *
     * @param personId the ID of the person.
     * @return a list of payment methods associated with the person.
     */
    public List<PaymentMethod> listPaymentMethodsByPersonId(Long personId) {
        return paymentMethodService.findByPersonId(personId);
    }

    /**
     * Lists payment methods by payment type.
     *
     * @param paymentType the type of the payment method.
     * @return a list of payment methods with the specified type.
     */
    public List<PaymentMethod> listPaymentMethodsByPaymentType(String paymentType) {
        return paymentMethodService.findByPaymentType(paymentType);
    }

    /**
     * Gets a payment method by card number.
     *
     * @param cardNumber the card number of the payment method.
     * @return the found payment method, or {@code null} if not found.
     */
    public PaymentMethod getPaymentMethodByCardNumber(String cardNumber) {
        return paymentMethodService.findByCardNumber(cardNumber);
    }

    /**
     * Deletes a payment method by ID.
     *
     * @param id the ID of the payment method to delete.
     */
    public void deletePaymentMethod(Long id) {
        paymentMethodService.delete(id);
    }
}
