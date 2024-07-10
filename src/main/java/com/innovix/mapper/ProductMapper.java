package com.innovix.mapper;

import com.innovix.dto.ProductDTO;
import com.innovix.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Product entity and ProductDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * Converts a Product entity to a ProductDTO.
     *
     * @param product The Product entity to convert.
     * @return The converted ProductDTO.
     */
    @Mapping(source = "id", target = "productId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "promotion.id", target = "promotionId", defaultExpression = "java(null)")
    ProductDTO toDto(Product product);

    /**
     * Converts a ProductDTO to a Product entity.
     *
     * @param productDTO The ProductDTO to convert.
     * @return The converted Product entity.
     */
    @Mapping(source = "productId", target = "id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "promotionId", target = "promotion.id", defaultExpression = "java(null)")
    Product toEntity(ProductDTO productDTO);
}
