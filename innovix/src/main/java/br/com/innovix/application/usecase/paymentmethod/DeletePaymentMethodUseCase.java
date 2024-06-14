package br.com.innovix.application.usecase.paymentmethod;

import br.com.innovix.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePaymentMethodUseCase {
    private final PaymentMethodRepository paymentMethodRepository;

    public DeletePaymentMethodUseCase(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void execute(Long paymentMethodId) {
        paymentMethodRepository.deleteById(paymentMethodId);
    }
}
