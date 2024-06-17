package com.innovix.mapper;

import com.innovix.dto.OrderDTO;
import com.innovix.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link Order} and {@link OrderDTO}.
 */
@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * Converts an {@link Order} entity to an {@link OrderDTO}.
     *
     * @param order the {@link Order} entity to convert.
     * @return the resulting {@link OrderDTO}.
     */
    @Mapping(source = "order.orderId", target = "orderId")
    @Mapping(source = "order.customer.personId", target = "customerId")
    @Mapping(source = "order.addressOrigin.addressId", target = "addressOriginId")
    @Mapping(source = "order.addressDestination.addressId", target = "addressDestinationId")
    @Mapping(source = "order.product.productId", target = "productId")
    OrderDTO toDto(Order order);

    /**
     * Converts an {@link OrderDTO} to an {@link Order} entity.
     *
     * @param orderDTO the {@link OrderDTO} to convert.
     * @return the resulting {@link Order} entity.
     */
    @Mapping(source = "orderDTO.orderId", target = "orderId")
    @Mapping(source = "orderDTO.customerId", target = "customer.personId")
    @Mapping(source = "orderDTO.addressOriginId", target = "addressOrigin.addressId")
    @Mapping(source = "orderDTO.addressDestinationId", target = "addressDestination.addressId")
    @Mapping(source = "orderDTO.productId", target = "product.productId")
    Order toEntity(OrderDTO orderDTO);
}
