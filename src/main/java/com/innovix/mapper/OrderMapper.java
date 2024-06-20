package com.innovix.mapper;

import com.innovix.dto.OrderDTO;
import com.innovix.entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "order.customer.id", target = "customerId")
    @Mapping(source = "order.addressOrigin.id", target = "addressOriginId")
    @Mapping(source = "order.addressDestination.id", target = "addressDestinationId")
    @Mapping(source = "order.product.id", target = "productId")
    OrderDTO toDto(PurchaseOrder order);

    @Mapping(source = "orderDTO.orderId", target = "id")
    @Mapping(source = "orderDTO.customerId", target = "customer.id")
    @Mapping(source = "orderDTO.addressOriginId", target = "addressOrigin.id")
    @Mapping(source = "orderDTO.addressDestinationId", target = "addressDestination.id")
    @Mapping(source = "orderDTO.productId", target = "product.id")
    PurchaseOrder toEntity(OrderDTO orderDTO);
}
