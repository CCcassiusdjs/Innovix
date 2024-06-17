package com.innovix.mapper;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link PaymentMethod} and {@link PaymentMethodDTO}.
 */
@Mapper
public interface PaymentMethodMapper {

    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    /**
     * Converts a {@link PaymentMethod} entity to a {@link PaymentMethodDTO}.
     *
     * @param paymentMethod the {@link PaymentMethod} entity to convert.
     * @return the resulting {@link PaymentMethodDTO}.
     */
    @Mapping(source = "paymentMethod.paymentMethodId", target = "paymentMethodId")
    @Mapping(source = "paymentMethod.person.personId", target = "personId")
    PaymentMethodDTO toDto(PaymentMethod paymentMethod);

    /**
     * Converts a {@link PaymentMethodDTO} to a {@link PaymentMethod} entity.
     *
     * @param paymentMethodDTO the {@link PaymentMethodDTO} to convert.
     * @return the resulting {@link PaymentMethod} entity.
     */
    @Mapping(source = "paymentMethodDTO.paymentMethodId", target = "paymentMethodId")
    @Mapping(source = "paymentMethodDTO.personId", target = "person.personId")
    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);
}
