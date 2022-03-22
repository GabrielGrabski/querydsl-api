package com.example.querydsl.person.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.example.querydsl.person.utils.PersonUtils.aPerson;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonResponseTest {

    @Test
    public void of_shouldConvertFromModel_whenCalled() {
        var response = PersonResponse.of(aPerson());

        assertThat(response)
                .extracting(PersonResponse::getFirstName,
                            PersonResponse::getLastName)
                .containsExactly("KAKAROTTO", "SABUGOSA");
    }
}