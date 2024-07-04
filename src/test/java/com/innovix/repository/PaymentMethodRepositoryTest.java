package com.innovix.repository;

import com.innovix.entity.PaymentMethod;
import com.innovix.entity.Person;
import java.util.List;

import com.innovix.repository.PaymentMethodRepository;
import com.innovix.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PaymentMethodRepositoryTest {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        person = new Person(1);
        person.setEmail("john.doe@example.com");
        person.setFullName("John Doe");
        person.setCpf("123.456.789-00");
        person.setPassword("password");
        person.setPhone("123456789");
        person.setBirthday(LocalDate.of(1990, 1, 1));
        person.setType(PersonType.USER);
        person = personRepository.save(person);

        PaymentMethod paymentMethod1 = new PaymentMethod(null, "CreditCard", "John Doe", "1234567890123456", LocalDate.of(2025, 12, 31), "123");
        PaymentMethod paymentMethod2 = new PaymentMethod(null, "DebitCard", "John Doe", "9876543210987654", LocalDate.of(2023, 6, 30), "456");
        paymentMethod1.setPerson(person);
        paymentMethod2.setPerson(person);
        paymentMethodRepository.save(paymentMethod1);
        paymentMethodRepository.save(paymentMethod2);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        paymentMethodRepository.deleteAll();
        personRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByPersonId() {
        List<PaymentMethod> foundMethods = paymentMethodRepository.findByPersonId(person.getId());
        assertNotNull(foundMethods);
        assertEquals(2, foundMethods.size());
    }

    @org.junit.jupiter.api.Test
    void findByPaymentType() {
        List<PaymentMethod> foundMethods = paymentMethodRepository.findByPaymentType("CreditCard");
        assertNotNull(foundMethods);
        assertEquals(1, foundMethods.size());
        assertEquals("CreditCard", foundMethods.get(0).getPaymentType());
    }

    @org.junit.jupiter.api.Test
    void findByCardNumber() {
        PaymentMethod foundMethod = paymentMethodRepository.findByCardNumber("9876543210987654");
        assertNotNull(foundMethod);
        assertEquals("John Doe", foundMethod.getCardHolder());
    }
}
