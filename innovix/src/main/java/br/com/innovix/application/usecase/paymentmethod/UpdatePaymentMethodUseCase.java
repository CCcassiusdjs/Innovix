package br.com.innovix.application.usecase.paymentmethod;

import br.com.innovix.domain.entity.PaymentMethod;
import br.com.innovix.domain.repository.PaymentMethodRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePaymentMethodUseCase {
    private final PaymentMethodRepository paymentMethodRepository;

    public UpdatePaymentMethodUseCase(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public Optional<PaymentMethod> execute(Long paymentMethodId, @Valid PaymentMethod paymentMethod) {
        return paymentMethodRepository.findById(paymentMethodId).map(existingPaymentMethod -> {
            existingPaymentMethod.setType(paymentMethod.getType());
            existingPaymentMethod.setCustomer(paymentMethod.getCustomer());
            return paymentMethodRepository.save(existingPaymentMethod);
        });
    }
}
