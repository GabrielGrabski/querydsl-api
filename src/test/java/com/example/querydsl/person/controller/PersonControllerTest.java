package com.example.querydsl.person.controller;

import com.example.querydsl.person.repository.PersonRepository;
import com.example.querydsl.person.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.querydsl.person.utils.PersonUtils.aPerson;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql("/scripts/person.sql")
@RunWith(SpringRunner.class)
public class PersonControllerTest {

    private static final String URL = "/api/person/";

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PersonRepository repository;
    @MockBean
    private PersonService service;

    @After
    public void deleteALl() {
        repository.deleteAll();
    }

    @Test
    @SneakyThrows
    public void findByFirstName_shouldBeCalledAndReturnOk() {
        mvc.perform(get(URL + "first-name/KAKAROTTO"))
                .andExpect(status().isOk());

        verify(service, times(1)).findByFirstName(eq("KAKAROTTO"));
    }

    @Test
    @SneakyThrows
    public void findByLastName_shouldBeCalledAndReturnOk() {
        mvc.perform(get(URL + "last-name/SILVA"))
                .andExpect(status().isOk());

        verify(service, times(1)).findByLastName(eq("SILVA"));
    }

    @Test
    @SneakyThrows
    public void findByIdAndFirstName_shouldBeCalledAndReturnOk() {
        mvc.perform(get(URL + "id-firstname")
                        .contentType("Application/json")
                        .content(mapper.writeValueAsString(aPerson())))
                .andExpect(status().isOk());

        verify(service, times(1))
                .findByIdAndFirstName(eq(1), eq("KAKAROTTO"));
    }

    @Test
    @SneakyThrows
    public void findByIdAndLastName_shouldBeCalledAndReturnOk() {
        mvc.perform(get(URL + "id-lastname")
                        .contentType("Application/json")
                        .content(mapper.writeValueAsString(aPerson())))
                .andExpect(status().isOk());

        verify(service, times(1))
                .findByIdAndLastName(eq(1), eq("SABUGOSA"));
    }

    @Test
    @SneakyThrows
    public void findByFirstAndLastName_shouldBeCalledAndReturnOk() {
        mvc.perform(get(URL + "full-name")
                        .contentType("Application/json")
                        .content(mapper.writeValueAsString(aPerson())))
                .andExpect(status().isOk());

        verify(service, times(1))
                .findByFirstAndLastName(eq("KAKAROTTO"), eq("SABUGOSA"));
    }
}
