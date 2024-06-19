package com.innovix.service;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import com.innovix.exception.UserExistentException;
import com.innovix.mapper.PersonMapper;
import com.innovix.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByEmail(username);
    }

    public boolean userExists(String email) {
        return personRepository.existsByEmail(email);
    }

    public List<PersonDTO> listAll() {
        return personRepository.findAll().stream()
                .map(PersonMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        return PersonMapper.INSTANCE.toDto(personRepository.findById(id).orElse(null));
    }

    public PersonDTO save(PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);
        person = personRepository.save(person);
        return PersonMapper.INSTANCE.toDto(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public void registerCustomer(PersonDTO personDTO) {
        if (userExists(personDTO.getEmail())) {
            throw new UserExistentException();
        }
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);
        person.setType(PersonType.CUSTOMER);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    public void registerEmployee(PersonDTO personDTO) {
        if (userExists(personDTO.getEmail())) {
            throw new UserExistentException();
        }
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);
        person.setType(PersonType.EMPLOYEE);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }
}
