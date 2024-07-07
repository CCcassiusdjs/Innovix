package com.innovix.mapper;

import com.innovix.dto.CategoryDTO;
import com.innovix.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "category.id", target = "categoryId")
    CategoryDTO toDto(Category category);

    @Mapping(source = "categoryDTO.categoryId", target = "id")
    Category toEntity(CategoryDTO categoryDTO);
}
