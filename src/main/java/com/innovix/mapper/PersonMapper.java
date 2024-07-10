package com.innovix.mapper;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Person entity and PersonDTO.
 * <p>
 * This interface uses MapStruct to generate the implementation code for the mappings.
 * </p>
 */
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    /**
     * Converts a Person entity to a PersonDTO.
     *
     * @param person The Person entity to convert.
     * @return The converted PersonDTO.
     */
    PersonDTO toDto(Person person);

    /**
     * Converts a PersonDTO to a Person entity.
     *
     * @param personDTO The PersonDTO to convert.
     * @return The converted Person entity.
     */
    Person toEntity(PersonDTO personDTO);
}
