package com.ora.springfinalproject.service;

import com.ora.springfinalproject.entity.BookEntity;
import com.ora.springfinalproject.entity.dto.BookDto;

import java.util.List;

public interface BookService {

    BookEntity getById(Long id);

    List<BookEntity> getAllByNameContainingIgnoreCase(String name);

    BookEntity save(BookDto bookDto);

    BookEntity update(BookEntity be);

    List<BookEntity> getAll();


}
