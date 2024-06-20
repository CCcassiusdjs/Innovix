package com.innovix.mapper;

import com.innovix.dto.ProductDTO;
import com.innovix.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.category.id", target = "categoryId")
    @Mapping(source = "product.promotion.id", target = "promotionId")
    ProductDTO toDto(Product product);

    @Mapping(source = "productDTO.productId", target = "id")
    @Mapping(source = "productDTO.categoryId", target = "category.id")
    @Mapping(source = "productDTO.promotionId", target = "promotion.id")
    Product toEntity(ProductDTO productDTO);
}
