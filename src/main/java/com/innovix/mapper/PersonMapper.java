package com.innovix.mapper;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper to convert between {@link Person} and {@link PersonDTO}.
 */
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    /**
     * Converts a {@link Person} entity to a {@link PersonDTO}.
     *
     * @param person the {@link Person} entity to convert.
     * @return the resulting {@link PersonDTO}.
     */
    @Mapping(source = "person.id", target = "personId")
    PersonDTO toDto(Person person);

    /**
     * Converts a {@link PersonDTO} to a {@link Person} entity.
     *
     * @param personDTO the {@link PersonDTO} to convert.
     * @return the resulting {@link Person} entity.
     */
    @Mapping(source = "personDTO.personId", target = "id")
    Person toEntity(PersonDTO personDTO);
}
