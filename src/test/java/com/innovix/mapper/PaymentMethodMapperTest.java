package com.innovix.mapper;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.entity.PaymentMethod;
import com.innovix.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodMapperTest {

    private final PaymentMethodMapper paymentMethodMapper = PaymentMethodMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        Person person = new Person();
        person.setId(1L);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1L);
        paymentMethod.setPerson(person);

        // Act
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // Assert
        assertNotNull(paymentMethodDTO);
        assertEquals(1L, paymentMethodDTO.getPaymentMethodId());
        assertEquals(1L, paymentMethodDTO.getPersonId());
    }

    @Test
    void toEntity() {
        // Arrange
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        paymentMethodDTO.setPaymentMethodId(1L);
        paymentMethodDTO.setPersonId(1L);

        // Act
        PaymentMethod paymentMethod = paymentMethodMapper.toEntity(paymentMethodDTO);

        // Assert
        assertNotNull(paymentMethod);
        assertEquals(1L, paymentMethod.getId());
        assertEquals(1L, paymentMethod.getPerson().getId());
    }
}
