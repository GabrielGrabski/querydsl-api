package com.example.querydsl.person.service;

import com.example.querydsl.commum.ValidationException;
import com.example.querydsl.person.dto.PersonResponse;
import com.example.querydsl.person.repository.PersonRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/scripts/person.sql")
@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @Autowired
    private PersonService service;
    @Autowired
    private PersonRepository repository;

    @After
    public void deleteAll() {
        repository.deleteAll();
    }

    @Test
    public void findByFirstName_shouldReturnAllPeopleWithGivenFirstName() {
        var person = service.findByFirstName("KAKAROTTO");
        assertThat(person)
                .extracting(PersonResponse::getFirstName, PersonResponse::getLastName)
                .hasSize(2)
                .containsExactlyInAnyOrder(
                        tuple("KAKAROTTO", "SABUGOSA"),
                        tuple("KAKAROTTO", "SILVA")
                );
    }

    @Test
    public void findByLastName_shouldReturnAllPeopleWithGivenLastName() {
        var person = service.findByLastName("SABUGOSA");
        assertThat(person)
                .extracting(PersonResponse::getFirstName, PersonResponse::getLastName)
                .hasSize(1)
                .containsExactlyInAnyOrder(tuple("KAKAROTTO", "SABUGOSA"));
    }

    @Test
    public void findById_shouldReturnPersonWithGivenId() {
        var person = service.findById(1);
        assertThat(person)
                .extracting(PersonResponse::getFirstName, PersonResponse::getLastName)
                .containsExactlyInAnyOrder("KAKAROTTO", "SABUGOSA");
    }

    @Test
    public void findByIdAndFirstName_shouldReturnPersonWithGivenIdAndFirstName() {
        var person = service.findByIdAndFirstName(1, "KAKAROTTO");
        assertThat(person)
                .extracting(PersonResponse::getFirstName, PersonResponse::getLastName)
                .containsExactlyInAnyOrder("KAKAROTTO", "SABUGOSA");
    }

    @Test
    public void findByIdAndLastName_shouldReturnPersonWithGivenIdAndLastName() {
        var person = service.findByIdAndLastName(2, "SILVA");
        assertThat(person)
                .extracting(PersonResponse::getFirstName, PersonResponse::getLastName)
                .containsExactlyInAnyOrder("KAKAROTTO", "SILVA");
    }

    @Test
    public void findByFirstAndLastName_shouldReturnPersonWithGivenName() {
        var person = service.findByFirstAndLastName("KAKAROTTO", "SILVA");
        assertThat(person)
                .hasSize(1)
                .extracting(PersonResponse::getFirstName, PersonResponse::getLastName)
                .containsExactlyInAnyOrder(tuple("KAKAROTTO", "SILVA"));
    }

    @Test
    public void findByFirstName_shouldReturnEmptyList_whenTheresNoPeople() {
        assertThat(service.findByFirstName("JULIUS")).isEmpty();
    }

    @Test
    public void findByLastName_shouldReturnEmptyList_whenTheresNoPeople() {
        assertThat(service.findByFirstName("SALNOBORO")).isEmpty();
    }

    @Test
    public void findById_shouldThrowEx_whenTheresNoPeople() {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.findById(100))
                .withMessage("#001 - User not found.");
    }

    @Test
    public void findByIdAndFirstName_shouldThrowEx_whenTheresNoPeople() {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.findByIdAndFirstName(100, "KAKAROTTO"))
                .withMessage("#001 - User not found.");
    }

    @Test
    public void findByIdAndLastName_shouldThrowEx_whenTheresNoPeople() {
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.findByIdAndLastName(100, "SILVA"))
                .withMessage("#001 - User not found.");
    }

    @Test
    public void findByFirstAndLastName_shouldReturnEmptyList_whenTheresNoPeople() {
        assertThat(service.findByFirstAndLastName("SAIR", "SALNOBORO")).isEmpty();
    }
}
