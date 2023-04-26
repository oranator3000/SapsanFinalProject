package com.ora.springfinalproject.handler;

import com.ora.springfinalproject.entity.dto.ExceptionResponse;
import com.ora.springfinalproject.exceptions.BookIsTakenException;
import com.ora.springfinalproject.exceptions.BookNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBookNotFoundException(BookNotFoundException ex){
        return new ResponseEntity<>(generateException(ex.getCode(),ex.getMessage(),ex.getDate()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookIsTakenException.class)
    public ResponseEntity<ExceptionResponse> handleBookIsTakenException(BookIsTakenException ex){
        return new ResponseEntity<>(generateException(ex.getCode(),ex.getMessage(),ex.getDate()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        List<ObjectError> list =  ex.getBindingResult().getAllErrors();
        for (ObjectError er: list) {

            String errorField = ((FieldError) er).getField();
            String errorMessage = er.getDefaultMessage();

            errors.put(errorField,errorMessage);
            
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse generateException(String code, String message, String date) {
        return new ExceptionResponse(message,code,date);
    }
}
