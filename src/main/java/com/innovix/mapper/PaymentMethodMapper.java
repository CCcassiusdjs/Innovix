package com.innovix.mapper;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between PaymentMethod entity and PaymentMethodDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface PaymentMethodMapper {

    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    /**
     * Converts a PaymentMethod entity to a PaymentMethodDTO.
     *
     * @param paymentMethod The PaymentMethod entity to convert.
     * @return The converted PaymentMethodDTO.
     */
    @Mapping(source = "id", target = "paymentMethodId")
    @Mapping(source = "person.id", target = "personId")
    PaymentMethodDTO toDto(PaymentMethod paymentMethod);

    /**
     * Converts a PaymentMethodDTO to a PaymentMethod entity.
     *
     * @param paymentMethodDTO The PaymentMethodDTO to convert.
     * @return The converted PaymentMethod entity.
     */
    @Mapping(source = "paymentMethodId", target = "id")
    @Mapping(source = "personId", target = "person.id")
    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);
}
