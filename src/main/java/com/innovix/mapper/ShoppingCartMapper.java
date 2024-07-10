package com.innovix.mapper;

import com.innovix.dto.ShoppingCartDTO;
import com.innovix.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between ShoppingCart entity and ShoppingCartDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface ShoppingCartMapper {

    ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

    /**
     * Converts a ShoppingCart entity to a ShoppingCartDTO.
     *
     * @param shoppingCart The ShoppingCart entity to convert.
     * @return The converted ShoppingCartDTO.
     */
    @Mapping(source = "id", target = "shoppingCartId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "product.id", target = "productId")
    ShoppingCartDTO toDto(ShoppingCart shoppingCart);

    /**
     * Converts a ShoppingCartDTO to a ShoppingCart entity.
     *
     * @param shoppingCartDTO The ShoppingCartDTO to convert.
     * @return The converted ShoppingCart entity.
     */
    @Mapping(source = "shoppingCartId", target = "id")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "productId", target = "product.id")
    ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO);
}
