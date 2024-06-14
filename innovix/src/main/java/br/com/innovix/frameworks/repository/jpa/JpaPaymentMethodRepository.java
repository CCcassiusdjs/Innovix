package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.PaymentMethod;
import br.com.innovix.domain.repository.PaymentMethodRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentMethodRepository extends JpaRepository<PaymentMethod, Long>, PaymentMethodRepository {
}
