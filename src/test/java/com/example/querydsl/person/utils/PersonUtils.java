package com.example.querydsl.person.utils;

import com.example.querydsl.person.model.Person;

public class PersonUtils {

    public static Person aPerson() {
        return Person.builder()
                .id(1)
                .firstName("KAKAROTTO")
                .lastName("SABUGOSA")
                .build();
    }
}
