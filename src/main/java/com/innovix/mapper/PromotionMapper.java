package com.innovix.mapper;

import com.innovix.dto.PromotionDTO;
import com.innovix.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Promotion entity and PromotionDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface PromotionMapper {

    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    /**
     * Converts a Promotion entity to a PromotionDTO.
     *
     * @param promotion The Promotion entity to convert.
     * @return The converted PromotionDTO.
     */
    @Mapping(source = "id", target = "promotionId")
    @Mapping(source = "employee.id", target = "employeeId")
    PromotionDTO toDto(Promotion promotion);

    /**
     * Converts a PromotionDTO to a Promotion entity.
     *
     * @param promotionDTO The PromotionDTO to convert.
     * @return The converted Promotion entity.
     */
    @Mapping(source = "promotionId", target = "id")
    @Mapping(source = "employeeId", target = "employee.id")
    Promotion toEntity(PromotionDTO promotionDTO);
}
