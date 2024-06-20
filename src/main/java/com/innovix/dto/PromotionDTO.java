package com.innovix.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PromotionDTO {
    private Long promotionId;
    private String description;
    private String season;
    private LocalDate initLocalDate;
    private LocalDate endLocalDate;
    private int duration;
    private double percentage;
    private int requiredQuantity;
    private int freeQuantity;
    private Long employeeId;
}
