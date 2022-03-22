package com.example.querydsl.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    private Integer id;
    private String firstName;
    private String lastName;
}
