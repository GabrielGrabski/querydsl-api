package com.example.querydsl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  EErrors {

    USER_NOT_FOUND("#001 - User not found.");

    private String description;
}
