package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository {
    PaymentMethod save(PaymentMethod paymentMethod);
    Optional<PaymentMethod> findById(Long paymentMethodId);
    List<PaymentMethod> findAll();
    void deleteById(Long paymentMethodId);
}
