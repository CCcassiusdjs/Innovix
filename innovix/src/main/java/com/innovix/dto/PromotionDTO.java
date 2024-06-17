package com.innovix.dto;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object for the Promotion entity.
 */
@Data
public class PromotionDTO {
    private Long promotionId;
    private String description;
    private String season;
    private Date initDate;
    private Date endDate;
    private Integer duration;
    private Double percentage;
    private Long employeeId;
}
