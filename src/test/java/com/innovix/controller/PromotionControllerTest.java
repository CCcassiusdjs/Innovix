package com.innovix.controller;

import com.innovix.dto.PromotionDTO;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(promotionController).build();
    }

    private Promotion createPromotion() {
        Promotion promotion = new Promotion();
        promotion.setId(1L);
        // Add more fields as necessary
        return promotion;
    }

    private PromotionDTO createPromotionDTO() {
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setPromotionId(1L);
        // Add more fields as necessary
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

        mockMvc.perform(post("/api/promotions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"promotionId\": 1}")) // Add more fields as necessary
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

        when(employeeUseCase.listPromotionsBySeason("Winter")).thenReturn(Collections.singletonList(promotion));
        when(promotionMapper.toDto(any(Promotion.class))).thenReturn(promotionDTO);

        mockMvc.perform(get("/api/promotions/season/Winter"))
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
    void deletePromotion() throws Exception {
        doNothing().when(employeeUseCase).deletePromotion(1L);

        mockMvc.perform(delete("/api/promotions/1"))
                .andExpect(status().isOk());
    }
}
