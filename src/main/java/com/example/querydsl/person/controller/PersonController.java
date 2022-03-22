package com.example.querydsl.person.controller;

import com.example.querydsl.person.dto.PersonRequest;
import com.example.querydsl.person.dto.PersonResponse;
import com.example.querydsl.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("first-name/{firstName}")
    public List<PersonResponse> findByFirstName(@PathVariable String firstName) {
        return service.findByFirstName(firstName);
    }

    @GetMapping("last-name/{lastName}")
    public List<PersonResponse> findByLastName(@PathVariable String lastName) {
        return service.findByLastName(lastName);
    }

    @GetMapping("{id}")
    public PersonResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("id-firstname")
    public PersonResponse findByIdAndFirstName(@RequestBody PersonRequest request) {
        return service.findByIdAndFirstName(request.getId(), request.getFirstName());
    }

    @GetMapping("id-lastname")
    public PersonResponse findByIdAndLastName(@RequestBody PersonRequest request) {
        return service.findByIdAndLastName(request.getId(), request.getLastName());
    }

    @GetMapping("full-name")
    public List<PersonResponse> findByFirstAndLastName(@RequestBody PersonRequest request) {
        return service.findByFirstAndLastName(request.getFirstName(), request.getLastName());
    }
}
