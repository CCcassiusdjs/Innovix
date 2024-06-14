package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.paymentmethod.CreatePaymentMethodUseCase;
import br.com.innovix.application.usecase.paymentmethod.DeletePaymentMethodUseCase;
import br.com.innovix.application.usecase.paymentmethod.GetPaymentMethodUseCase;
import br.com.innovix.application.usecase.paymentmethod.UpdatePaymentMethodUseCase;
import br.com.innovix.domain.entity.PaymentMethod;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paymentmethods")
public class PaymentMethodController {
    @Autowired
    private CreatePaymentMethodUseCase createPaymentMethodUseCase;

    @Autowired
    private GetPaymentMethodUseCase getPaymentMethodUseCase;

    @Autowired
    private UpdatePaymentMethodUseCase updatePaymentMethodUseCase;

    @Autowired
    private DeletePaymentMethodUseCase deletePaymentMethodUseCase;

    @PostMapping
    public PaymentMethod createPaymentMethod(@RequestBody @Valid PaymentMethod paymentMethod) {
        return createPaymentMethodUseCase.execute(paymentMethod);
    }

    @GetMapping("/{id}")
    public Optional<PaymentMethod> getPaymentMethod(@PathVariable Long id) {
        return getPaymentMethodUseCase.execute(id);
    }

    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return getPaymentMethodUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<PaymentMethod> updatePaymentMethod(@PathVariable Long id, @RequestBody @Valid PaymentMethod paymentMethod) {
        return updatePaymentMethodUseCase.execute(id, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentMethod(@PathVariable Long id) {
        deletePaymentMethodUseCase.execute(id);
    }
}
