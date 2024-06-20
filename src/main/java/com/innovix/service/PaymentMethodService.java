package com.innovix.service;

import com.innovix.entity.PaymentMethod;
import com.innovix.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<PaymentMethod> listAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

    public List<PaymentMethod> findByPersonId(Long personId) {
        return paymentMethodRepository.findByPersonId(personId);
    }

    public List<PaymentMethod> findByPaymentType(String paymentType) {
        return paymentMethodRepository.findByPaymentType(paymentType);
    }

    public PaymentMethod findByCardNumber(String cardNumber) {
        return paymentMethodRepository.findByCardNumber(cardNumber);
    }

    public void delete(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
