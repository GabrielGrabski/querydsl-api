package com.example.querydsl.commum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageException {

    private String msg;
    private String field;

    public MessageException(String msg) {
        this.msg = msg;
    }
}
