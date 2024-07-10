package com.innovix.mapper;

import com.innovix.dto.CategoryDTO;
import com.innovix.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Category entity and CategoryDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    /**
     * Converts a Category entity to a CategoryDTO.
     *
     * @param category The Category entity to convert.
     * @return The converted CategoryDTO.
     */
    @Mapping(source = "id", target = "categoryId")
    CategoryDTO toDto(Category category);

    /**
     * Converts a CategoryDTO to a Category entity.
     *
     * @param categoryDTO The CategoryDTO to convert.
     * @return The converted Category entity.
     */
    @Mapping(source = "categoryId", target = "id")
    Category toEntity(CategoryDTO categoryDTO);
}
