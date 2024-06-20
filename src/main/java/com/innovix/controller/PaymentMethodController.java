package com.innovix.controller;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.mapper.PaymentMethodMapper;
import com.innovix.usecase.CustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final CustomerUseCase customerUseCase;

    @Autowired
    public PaymentMethodController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<PaymentMethodDTO> listAll() {
        return customerUseCase.listAllPaymentMethods().stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public PaymentMethodDTO save(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        return PaymentMethodMapper.INSTANCE.toDto(
                customerUseCase.createPaymentMethod(PaymentMethodMapper.INSTANCE.toEntity(paymentMethodDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public PaymentMethodDTO getById(@PathVariable Long id) {
        return PaymentMethodMapper.INSTANCE.toDto(customerUseCase.getPaymentMethodById(id));
    }

    @GetMapping("/person/{personId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<PaymentMethodDTO> listByPersonId(@PathVariable Long personId) {
        return customerUseCase.listPaymentMethodsByPersonId(personId).stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/type/{paymentType}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<PaymentMethodDTO> listByPaymentType(@PathVariable String paymentType) {
        return customerUseCase.listPaymentMethodsByPaymentType(paymentType).stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/card/{cardNumber}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public PaymentMethodDTO getByCardNumber(@PathVariable String cardNumber) {
        return PaymentMethodMapper.INSTANCE.toDto(customerUseCase.getPaymentMethodByCardNumber(cardNumber));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public void delete(@PathVariable Long id) {
        customerUseCase.deletePaymentMethod(id);
    }
}
