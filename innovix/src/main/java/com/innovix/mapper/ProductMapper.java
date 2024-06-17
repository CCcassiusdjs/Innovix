package com.innovix.mapper;

import com.innovix.dto.ProductDTO;
import com.innovix.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link Product} and {@link ProductDTO}.
 */
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * Converts a {@link Product} entity to a {@link ProductDTO}.
     *
     * @param product the {@link Product} entity to convert.
     * @return the resulting {@link ProductDTO}.
     */
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "product.category.categoryId", target = "categoryId")
    @Mapping(source = "product.promotion.promotionId", target = "promotionId")
    ProductDTO toDto(Product product);

    /**
     * Converts a {@link ProductDTO} to a {@link Product} entity.
     *
     * @param productDTO the {@link ProductDTO} to convert.
     * @return the resulting {@link Product} entity.
     */
    @Mapping(source = "productDTO.productId", target = "productId")
    @Mapping(source = "productDTO.categoryId", target = "category.categoryId")
    @Mapping(source = "productDTO.promotionId", target = "promotion.promotionId")
    Product toEntity(ProductDTO productDTO);
}
