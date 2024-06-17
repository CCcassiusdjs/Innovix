package com.innovix.mapper;

import com.innovix.dto.ShoppingCartDTO;
import com.innovix.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link ShoppingCart} and {@link ShoppingCartDTO}.
 */
@Mapper
public interface ShoppingCartMapper {

    ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

    /**
     * Converts a {@link ShoppingCart} entity to a {@link ShoppingCartDTO}.
     *
     * @param shoppingCart the {@link ShoppingCart} entity to convert.
     * @return the resulting {@link ShoppingCartDTO}.
     */
    @Mapping(source = "shoppingCart.shoppingCartId", target = "shoppingCartId")
    @Mapping(source = "shoppingCart.customer.personId", target = "customerId")
    @Mapping(source = "shoppingCart.product.productId", target = "productId")
    ShoppingCartDTO toDto(ShoppingCart shoppingCart);

    /**
     * Converts a {@link ShoppingCartDTO} to a {@link ShoppingCart} entity.
     *
     * @param shoppingCartDTO the {@link ShoppingCartDTO} to convert.
     * @return the resulting {@link ShoppingCart} entity.
     */
    @Mapping(source = "shoppingCartDTO.shoppingCartId", target = "shoppingCartId")
    @Mapping(source = "shoppingCartDTO.customerId", target = "customer.personId")
    @Mapping(source = "shoppingCartDTO.productId", target = "product.productId")
    ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO);
}
