package com.ora.springfinalproject.controller;


import com.ora.springfinalproject.entity.dto.BookDto;
import com.ora.springfinalproject.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookServiceImpl bookService;


    @PostMapping("/save-book")
    public String saveNewBook(@RequestBody BookDto bookDto){
        bookService.save(bookDto);
        return "Added!";
    }



}
