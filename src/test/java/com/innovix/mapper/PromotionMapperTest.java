package com.innovix.mapper;

import com.innovix.dto.PromotionDTO;
import com.innovix.entity.Person;
import com.innovix.entity.Promotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromotionMapperTest {

    private final PromotionMapper promotionMapper = PromotionMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        Promotion promotion = new Promotion();
        promotion.setId(1L);
        Person employee = new Person();
        employee.setId(2L);
        promotion.setEmployee(employee);

        // Act
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        // Assert
        assertNotNull(promotionDTO);
        assertEquals(1L, promotionDTO.getPromotionId());
        assertEquals(2L, promotionDTO.getEmployeeId());
    }

    @Test
    void toEntity() {
        // Arrange
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setPromotionId(1L);
        promotionDTO.setEmployeeId(2L);

        // Act
        Promotion promotion = promotionMapper.toEntity(promotionDTO);

        // Assert
        assertNotNull(promotion);
        assertEquals(1L, promotion.getId());
        assertNotNull(promotion.getEmployee());
        assertEquals(2L, promotion.getEmployee().getId());
    }
}
