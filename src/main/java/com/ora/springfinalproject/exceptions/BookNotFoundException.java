package com.ora.springfinalproject.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookNotFoundException extends RuntimeException{

    private String message;
    private String code;
    private String date;

    public BookNotFoundException(String message,String code){
        super(message);
        this.code = code;
        this.message = message;
        this.date = LocalDateTime.now().toString();
    }
}
