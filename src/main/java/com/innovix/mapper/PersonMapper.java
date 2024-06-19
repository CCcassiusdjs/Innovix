package com.innovix.mapper;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "type", target = "type")
    PersonDTO toDto(Person person);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "type", target = "type")
    Person toEntity(PersonDTO personDTO);
}
