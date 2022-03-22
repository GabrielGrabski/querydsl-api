package com.example.querydsl.person.service;

import com.example.querydsl.commum.ValidationException;
import com.example.querydsl.person.dto.PersonResponse;
import com.example.querydsl.person.repository.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.querydsl.enums.EErrors.USER_NOT_FOUND;

@Service
public class PersonService {

    @Autowired
    private PersonRepositoryImpl repository;

    public List<PersonResponse> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName)
                .stream()
                .map(PersonResponse::of)
                .collect(Collectors.toList());
    }

    public List<PersonResponse> findByLastName(String lastName) {
        return repository.findByLastName(lastName)
                .stream()
                .map(PersonResponse::of)
                .collect(Collectors.toList());
    }

    public PersonResponse findById(Integer id) {
        var person = repository.findById(id).orElseThrow(() ->
                new ValidationException(USER_NOT_FOUND.getDescription()));
        return PersonResponse.of(person);
    }

    public PersonResponse findByIdAndFirstName(Integer id, String firstName) {
        var person = repository.findByIdAndFirstName(id, firstName).orElseThrow(() ->
                new ValidationException(USER_NOT_FOUND.getDescription()));
        return PersonResponse.of(person);
    }

    public PersonResponse findByIdAndLastName(Integer id, String lastName) {
        var person = repository.findByIdAndLastName(id, lastName).orElseThrow(() ->
                new ValidationException(USER_NOT_FOUND.getDescription()));
        return PersonResponse.of(person);
    }

    public List<PersonResponse> findByFirstAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(PersonResponse::of)
                .collect(Collectors.toList());
    }
}
