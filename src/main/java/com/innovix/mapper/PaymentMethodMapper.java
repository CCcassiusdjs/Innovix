package com.innovix.mapper;

import com.innovix.dto.PaymentMethodDTO;
import com.innovix.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMethodMapper {

    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    @Mapping(source = "paymentMethod.person.id", target = "personId")
    PaymentMethodDTO toDto(PaymentMethod paymentMethod);

    @Mapping(source = "paymentMethodDTO.paymentMethodId", target = "id")
    @Mapping(source = "paymentMethodDTO.personId", target = "person.id")
    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);
}
