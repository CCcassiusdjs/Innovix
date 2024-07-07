package com.innovix.mapper;

import com.innovix.dto.PromotionDTO;
import com.innovix.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromotionMapper {

    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    @Mapping(source = "promotion.id", target = "promotionId")
    @Mapping(source = "promotion.employee.id", target = "employeeId")
    PromotionDTO toDto(Promotion promotion);

    @Mapping(source = "promotionDTO.promotionId", target = "id")
    @Mapping(source = "promotionDTO.employeeId", target = "employee.id")
    Promotion toEntity(PromotionDTO promotionDTO);
}
