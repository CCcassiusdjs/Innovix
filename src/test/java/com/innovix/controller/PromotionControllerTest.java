package com.innovix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.innovix.dto.PromotionDTO;
import com.innovix.entity.Person;
import com.innovix.entity.Promotion;
import com.innovix.mapper.PromotionMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PromotionControllerTest {

    @Mock
    private EmployeeUseCase employeeUseCase;

    @Mock
    private PromotionMapper promotionMapper;

    @InjectMocks
    private PromotionController promotionController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(promotionController)
                .setValidator(new LocalValidatorFactoryBean())
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private Promotion createPromotion() {
        Promotion promotion = new Promotion();
        promotion.setId(1L);
        promotion.setDescription("Summer Sale");
        promotion.setSeason("Summer");
        promotion.setInitLocalDate(LocalDate.of(2023, 6, 1));
        promotion.setEndLocalDate(LocalDate.of(2023, 9, 1));
        promotion.setDuration(90);
        promotion.setPercentage(10.0);
        promotion.setRequiredQuantity(2);
        promotion.setFreeQuantity(1);
        promotion.setEmployee(new Person());
        return promotion;
    }

    private PromotionDTO createPromotionDTO() {
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setPromotionId(1L);
        promotionDTO.setDescription("Summer Sale");
        promotionDTO.setSeason("Summer");
        promotionDTO.setInitLocalDate(LocalDate.of(2023, 6, 1));
        promotionDTO.setEndLocalDate(LocalDate.of(2023, 9, 1));
        promotionDTO.setDuration(90);
        promotionDTO.setPercentage(10.0);
        promotionDTO.setRequiredQuantity(2);
        promotionDTO.setFreeQuantity(1);
        promotionDTO.setEmployeeId(1L);
        return promotionDTO;
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listAll() throws Exception {
        Promotion promotion = createPromotion();
        PromotionDTO promotionDTO = createPromotionDTO();

        when(employeeUseCase.listAllPromotions()).thenReturn(Collections.singletonList(promotion));
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        mockMvc.perform(get("/api/promotions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].promotionId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void save() throws Exception {
        Promotion promotion = createPromotion();
        PromotionDTO promotionDTO = createPromotionDTO();

        when(promotionMapper.toEntity(any(PromotionDTO.class))).thenReturn(promotion);
        when(employeeUseCase.createPromotion(any(Promotion.class))).thenReturn(promotion);
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        String promotionJson = objectMapper.writeValueAsString(promotionDTO);

        mockMvc.perform(post("/api/promotions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(promotionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.promotionId").value(1L));
    }


    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void getById() throws Exception {
        Promotion promotion = createPromotion();
        PromotionDTO promotionDTO = createPromotionDTO();

        when(employeeUseCase.getPromotionById(1L)).thenReturn(promotion);
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        mockMvc.perform(get("/api/promotions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.promotionId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listBySeason() throws Exception {
        Promotion promotion = createPromotion();
        PromotionDTO promotionDTO = createPromotionDTO();

        when(employeeUseCase.listPromotionsBySeason("Summer")).thenReturn(Collections.singletonList(promotion));
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        mockMvc.perform(get("/api/promotions/season/Summer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].promotionId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByInitLocalDateBefore() throws Exception {
        Promotion promotion = createPromotion();
        PromotionDTO promotionDTO = createPromotionDTO();

        when(employeeUseCase.listPromotionsByInitLocalDateBefore(any(LocalDate.class))).thenReturn(Collections.singletonList(promotion));
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        mockMvc.perform(get("/api/promotions/init-date")
                        .param("date", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].promotionId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByEndLocalDateAfter() throws Exception {
        Promotion promotion = createPromotion();
        PromotionDTO promotionDTO = createPromotionDTO();

        when(employeeUseCase.listPromotionsByEndLocalDateAfter(any(LocalDate.class))).thenReturn(Collections.singletonList(promotion));
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        mockMvc.perform(get("/api/promotions/end-date")
                        .param("date", "2023-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].promotionId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByEmployeeId() throws Exception {
        Promotion promotion = createPromotion();
        PromotionDTO promotionDTO = createPromotionDTO();

        when(employeeUseCase.listPromotionsByEmployeeId(1L)).thenReturn(Collections.singletonList(promotion));
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        mockMvc.perform(get("/api/promotions/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].promotionId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void deletePromotion() throws Exception {
        doNothing().when(employeeUseCase).deletePromotion(1L);

        mockMvc.perform(delete("/api/promotions/1"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listAll_Empty() throws Exception {
        when(employeeUseCase.listAllPromotions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/promotions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
