package com.innovix.mapper;

import com.innovix.dto.PromotionDTO;
import com.innovix.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link Promotion} and {@link PromotionDTO}.
 */
@Mapper
public interface PromotionMapper {

    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    /**
     * Converts a {@link Promotion} entity to a {@link PromotionDTO}.
     *
     * @param promotion the {@link Promotion} entity to convert.
     * @return the resulting {@link PromotionDTO}.
     */
    @Mapping(source = "promotion.id", target = "promotionId")
    @Mapping(source = "promotion.employee.id", target = "employeeId")
    PromotionDTO toDto(Promotion promotion);

    /**
     * Converts a {@link PromotionDTO} to a {@link Promotion} entity.
     *
     * @param promotionDTO the {@link PromotionDTO} to convert.
     * @return the resulting {@link Promotion} entity.
     */
    @Mapping(source = "promotionDTO.promotionId", target = "id")
    @Mapping(source = "promotionDTO.employeeId", target = "employee.id")
    Promotion toEntity(PromotionDTO promotionDTO);
}
