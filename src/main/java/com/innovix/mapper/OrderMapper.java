package com.innovix.mapper;

import com.innovix.dto.OrderDTO;
import com.innovix.entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between PurchaseOrder entity and OrderDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * Converts a PurchaseOrder entity to an OrderDTO.
     *
     * @param order The PurchaseOrder entity to convert.
     * @return The converted OrderDTO.
     */
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "addressOrigin.id", target = "addressOriginId")
    @Mapping(source = "addressDestination.id", target = "addressDestinationId")
    @Mapping(source = "product.id", target = "productId")
    OrderDTO toDto(PurchaseOrder order);

    /**
     * Converts an OrderDTO to a PurchaseOrder entity.
     *
     * @param orderDTO The OrderDTO to convert.
     * @return The converted PurchaseOrder entity.
     */
    @Mapping(source = "orderId", target = "id")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "addressOriginId", target = "addressOrigin.id")
    @Mapping(source = "addressDestinationId", target = "addressDestination.id")
    @Mapping(source = "productId", target = "product.id")
    PurchaseOrder toEntity(OrderDTO orderDTO);
}
