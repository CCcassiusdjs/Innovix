package com.innovix.controller;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.mapper.PaymentMethodMapper;
import com.innovix.usecase.CustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing payment methods.
 */
@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final CustomerUseCase customerUseCase;

    @Autowired
    public PaymentMethodController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    /**
     * Lists all payment methods.
     *
     * @return A list of all payment methods.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<PaymentMethodDTO> listAll() {
        return customerUseCase.listAllPaymentMethods().stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new payment method.
     *
     * @param paymentMethodDTO The payment method data transfer object.
     * @return The saved payment method data transfer object.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public PaymentMethodDTO save(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        return PaymentMethodMapper.INSTANCE.toDto(
                customerUseCase.createPaymentMethod(PaymentMethodMapper.INSTANCE.toEntity(paymentMethodDTO))
        );
    }

    /**
     * Gets a payment method by ID.
     *
     * @param id The ID of the payment method.
     * @return The payment method data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public PaymentMethodDTO getById(@PathVariable Long id) {
        return PaymentMethodMapper.INSTANCE.toDto(customerUseCase.getPaymentMethodById(id));
    }

    /**
     * Lists payment methods by person ID.
     *
     * @param personId The ID of the person.
     * @return A list of payment methods associated with the specified person.
     */
    @GetMapping("/person/{personId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<PaymentMethodDTO> listByPersonId(@PathVariable Long personId) {
        return customerUseCase.listPaymentMethodsByPersonId(personId).stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists payment methods by payment type.
     *
     * @param paymentType The type of payment method.
     * @return A list of payment methods of the specified type.
     */
    @GetMapping("/type/{paymentType}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<PaymentMethodDTO> listByPaymentType(@PathVariable String paymentType) {
        return customerUseCase.listPaymentMethodsByPaymentType(paymentType).stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets a payment method by card number.
     *
     * @param cardNumber The card number of the payment method.
     * @return The payment method data transfer object.
     */
    @GetMapping("/card/{cardNumber}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public PaymentMethodDTO getByCardNumber(@PathVariable String cardNumber) {
        return PaymentMethodMapper.INSTANCE.toDto(customerUseCase.getPaymentMethodByCardNumber(cardNumber));
    }

    /**
     * Deletes a payment method by ID.
     *
     * @param id The ID of the payment method to delete.
     * @return A response entity with no content.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerUseCase.deletePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}
