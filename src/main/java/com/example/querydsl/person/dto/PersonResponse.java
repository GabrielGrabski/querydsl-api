package com.example.querydsl.person.dto;

import com.example.querydsl.person.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private String firstName;
    private String lastName;

    public static PersonResponse of(Person person) {
        var response = new PersonResponse();
        BeanUtils.copyProperties(person, response);
        return response;
    }
}
