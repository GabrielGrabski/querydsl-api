package com.example.querydsl.person.repository;

import com.example.querydsl.person.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepositoryCustom {

    List<Person> findByFirstName(String firstName);

    List<Person> findByLastName(String lastName);

    Optional<Person> findById(Integer id);

    Optional<Person> findByIdAndFirstName(Integer id, String firstName);

    Optional<Person> findByIdAndLastName(Integer id, String lastName);

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
