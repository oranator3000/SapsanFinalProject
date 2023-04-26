package com.ora.springfinalproject.service;

import com.ora.springfinalproject.entity.BookEntity;
import com.ora.springfinalproject.entity.dto.BookDto;
import com.ora.springfinalproject.exceptions.BookNotFoundException;
import com.ora.springfinalproject.repository.BookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookEntityRepository bookEntityRepository;

    @Autowired
    public BookServiceImpl(BookEntityRepository bookEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
    }

    @Override
    public BookEntity getById(Long id) {
        return bookEntityRepository.findById(id).orElseThrow(()->
                new BookNotFoundException("There is no such book with id","B6"));
    }

    @Override
    public List<BookEntity> getAllByNameContainingIgnoreCase(String name) {
        return bookEntityRepository.getAllByNameContainingIgnoreCase(name);
    }

    @Override
    public BookEntity save(BookDto bookDto) {
        BookEntity b = new BookEntity();
        b.setName(bookDto.getName());
        b.setGenre(bookDto.getGenre());
        return bookEntityRepository.save(b);
    }

    @Override
    public BookEntity update(BookEntity be) {
        return bookEntityRepository.save(be);
    }

    @Override
    public List<BookEntity> getAll() {
        return bookEntityRepository.findAll();
    }
}
