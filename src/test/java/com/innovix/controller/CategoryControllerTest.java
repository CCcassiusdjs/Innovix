package com.innovix.controller;

import com.innovix.dto.CategoryDTO;
import com.innovix.mapper.CategoryMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeUseCase employeeUseCase;

    @BeforeEach
    void setUp() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(1L);
        categoryDTO.setName("Test Category");

        Mockito.when(employeeUseCase.listAllCategories())
                .thenReturn(Collections.singletonList(CategoryMapper.INSTANCE.toEntity(categoryDTO)));

        Mockito.when(employeeUseCase.createCategory(any()))
                .thenReturn(CategoryMapper.INSTANCE.toEntity(categoryDTO));

        Mockito.when(employeeUseCase.getCategoryById(anyLong()))
                .thenReturn(CategoryMapper.INSTANCE.toEntity(categoryDTO));

        Mockito.when(employeeUseCase.getCategoryByName(anyString()))
                .thenReturn(CategoryMapper.INSTANCE.toEntity(categoryDTO));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void listAll() throws Exception {
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Test Category"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void save() throws Exception {
        String categoryJson = "{\"categoryId\":1,\"name\":\"Test Category\"}";

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Category"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void getById() throws Exception {
        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Category"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void getByName() throws Exception {
        mockMvc.perform(get("/api/categories/name/Test Category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Category"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void deleteCategory() throws Exception {
        mockMvc.perform(delete("/api/categories/1"))
                .andExpect(status().isOk());
    }
}
