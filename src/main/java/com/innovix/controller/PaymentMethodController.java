package com.innovix.controller;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.mapper.PaymentMethodMapper;
import com.innovix.usecase.PaymentMethodUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodUseCase paymentMethodUseCase;

    @Autowired
    public PaymentMethodController(PaymentMethodUseCase paymentMethodUseCase) {
        this.paymentMethodUseCase = paymentMethodUseCase;
    }

    @GetMapping
    public List<PaymentMethodDTO> listAll() {
        return paymentMethodUseCase.listAllPaymentMethods().stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public PaymentMethodDTO save(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        return PaymentMethodMapper.INSTANCE.toDto(
                paymentMethodUseCase.createPaymentMethod(PaymentMethodMapper.INSTANCE.toEntity(paymentMethodDTO))
        );
    }

    @GetMapping("/{id}")
    public PaymentMethodDTO getById(@PathVariable Long id) {
        return PaymentMethodMapper.INSTANCE.toDto(paymentMethodUseCase.getPaymentMethodById(id));
    }

    @GetMapping("/person/{personId}")
    public List<PaymentMethodDTO> listByPersonId(@PathVariable Long personId) {
        return paymentMethodUseCase.listPaymentMethodsByPersonId(personId).stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/type/{paymentType}")
    public List<PaymentMethodDTO> listByPaymentType(@PathVariable String paymentType) {
        return paymentMethodUseCase.listPaymentMethodsByPaymentType(paymentType).stream()
                .map(PaymentMethodMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/card/{cardNumber}")
    public PaymentMethodDTO getByCardNumber(@PathVariable String cardNumber) {
        return PaymentMethodMapper.INSTANCE.toDto(paymentMethodUseCase.getPaymentMethodByCardNumber(cardNumber));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentMethodUseCase.deletePaymentMethod(id);
    }
}
