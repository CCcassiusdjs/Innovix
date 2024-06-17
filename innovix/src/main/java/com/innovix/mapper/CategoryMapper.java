package com.innovix.mapper;

import com.innovix.dto.CategoryDTO;
import com.innovix.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link Category} and {@link CategoryDTO}.
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    /**
     * Converts a {@link Category} entity to a {@link CategoryDTO}.
     *
     * @param category the {@link Category} entity to convert.
     * @return the resulting {@link CategoryDTO}.
     */
    @Mapping(source = "category.categoryId", target = "categoryId")
    CategoryDTO toDto(Category category);

    /**
     * Converts a {@link CategoryDTO} to a {@link Category} entity.
     *
     * @param categoryDTO the {@link CategoryDTO} to convert.
     * @return the resulting {@link Category} entity.
     */
    @Mapping(source = "categoryDTO.categoryId", target = "categoryId")
    Category toEntity(CategoryDTO categoryDTO);
}
