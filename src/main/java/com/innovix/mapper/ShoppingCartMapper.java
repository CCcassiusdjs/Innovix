package com.innovix.mapper;

import com.innovix.dto.ShoppingCartDTO;
import com.innovix.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

    @Mapping(source = "shoppingCart.id", target = "shoppingCartId")
    @Mapping(source = "shoppingCart.customer.id", target = "customerId")
    @Mapping(source = "shoppingCart.product.id", target = "productId")
    ShoppingCartDTO toDto(ShoppingCart shoppingCart);

    @Mapping(source = "shoppingCartDTO.shoppingCartId", target = "id")
    @Mapping(source = "shoppingCartDTO.customerId", target = "customer.id")
    @Mapping(source = "shoppingCartDTO.productId", target = "product.id")
    ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO);
}
