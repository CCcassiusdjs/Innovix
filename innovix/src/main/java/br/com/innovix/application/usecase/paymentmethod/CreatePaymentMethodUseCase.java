package br.com.innovix.application.usecase.paymentmethod;

import br.com.innovix.domain.entity.PaymentMethod;
import br.com.innovix.domain.repository.PaymentMethodRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentMethodUseCase {
    private final PaymentMethodRepository paymentMethodRepository;

    public CreatePaymentMethodUseCase(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethod execute(@Valid PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }
}
