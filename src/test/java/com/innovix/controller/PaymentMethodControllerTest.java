package com.innovix.controller;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.entity.PaymentMethod;
import com.innovix.mapper.PaymentMethodMapper;
import com.innovix.usecase.CustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

class PaymentMethodControllerTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @InjectMocks
    private PaymentMethodController paymentMethodController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentMethodController).build();
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void listAll() throws Exception {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardNumber("1234567890123456");

        when(customerUseCase.listAllPaymentMethods()).thenReturn(Collections.singletonList(paymentMethod));

        mockMvc.perform(get("/api/payment-methods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cardNumber").value("1234567890123456"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void savePaymentMethod() throws Exception {
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        paymentMethodDTO.setCardNumber("1234567890123456");

        when(customerUseCase.createPaymentMethod(any())).thenReturn(PaymentMethodMapper.INSTANCE.toEntity(paymentMethodDTO));

        mockMvc.perform(post("/api/payment-methods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardNumber\":\"1234567890123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void getById() throws Exception {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardNumber("1234567890123456");

        when(customerUseCase.getPaymentMethodById(1L)).thenReturn(paymentMethod);

        mockMvc.perform(get("/api/payment-methods/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void listByPersonId() throws Exception {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardNumber("1234567890123456");

        when(customerUseCase.listPaymentMethodsByPersonId(1L)).thenReturn(Collections.singletonList(paymentMethod));

        mockMvc.perform(get("/api/payment-methods/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cardNumber").value("1234567890123456"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void listByPaymentType() throws Exception {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardNumber("1234567890123456");

        when(customerUseCase.listPaymentMethodsByPaymentType("CREDIT")).thenReturn(Collections.singletonList(paymentMethod));

        mockMvc.perform(get("/api/payment-methods/type/CREDIT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cardNumber").value("1234567890123456"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void getByCardNumber() throws Exception {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardNumber("1234567890123456");

        when(customerUseCase.getPaymentMethodByCardNumber("1234567890123456")).thenReturn(paymentMethod);

        mockMvc.perform(get("/api/payment-methods/card/1234567890123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void deletePaymentMethod() throws Exception {
        doNothing().when(customerUseCase).deletePaymentMethod(1L);

        mockMvc.perform(delete("/api/payment-methods/1"))
                .andExpect(status().isNoContent());
    }
}
