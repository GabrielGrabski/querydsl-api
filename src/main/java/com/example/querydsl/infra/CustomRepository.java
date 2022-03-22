package com.example.querydsl.infra;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomRepository {

    @PersistenceContext
    protected EntityManager entityManager;
}
