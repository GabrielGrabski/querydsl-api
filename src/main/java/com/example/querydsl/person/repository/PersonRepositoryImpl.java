package com.example.querydsl.person.repository;

import com.example.querydsl.infra.CustomRepository;
import com.example.querydsl.person.model.Person;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.querydsl.person.model.QPerson.person;

@Repository
public class PersonRepositoryImpl extends CustomRepository implements PersonRepositoryCustom {

    @Override
    public List<Person> findByFirstName(String firstName) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(person)
                .where(person.firstName.like(firstName.toUpperCase()))
                .orderBy(person.firstName.asc(), person.lastName.asc())
                .fetch();
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(person)
                .where(person.lastName.like(lastName.toUpperCase()))
                .orderBy(person.firstName.asc(), person.lastName.asc())
                .fetch();
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return Optional.ofNullable(new JPAQueryFactory(entityManager)
                .selectFrom(person)
                .where(person.id.eq(id))
                .fetchOne());
    }

    @Override
    public Optional<Person> findByIdAndFirstName(Integer id, String firstName) {
        return Optional.ofNullable(new JPAQueryFactory(entityManager)
                .selectFrom(person)
                .where(person.id.eq(id)
                        .and(person.firstName.like(firstName.toUpperCase())))
                .fetchOne());
    }

    @Override
    public Optional<Person> findByIdAndLastName(Integer id, String lastName) {
        return Optional.ofNullable(new JPAQueryFactory(entityManager)
                .selectFrom(person)
                .where(person.id.eq(id)
                        .and(person.lastName.like(lastName)))
                .fetchOne()
        );
    }

    @Override
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(person)
                .where(person.firstName.like(firstName)
                        .and(person.lastName.like(lastName)))
                .orderBy(person.firstName.asc(), person.lastName.asc())
                .fetch();
    }
}
