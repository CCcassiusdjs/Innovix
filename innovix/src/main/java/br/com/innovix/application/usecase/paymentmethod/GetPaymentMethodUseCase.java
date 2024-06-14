package br.com.innovix.application.usecase.paymentmethod;

import br.com.innovix.domain.entity.PaymentMethod;
import br.com.innovix.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetPaymentMethodUseCase {
    private final PaymentMethodRepository paymentMethodRepository;

    public GetPaymentMethodUseCase(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public Optional<PaymentMethod> execute(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId);
    }

    public List<PaymentMethod> execute() {
        return paymentMethodRepository.findAll();
    }
}
