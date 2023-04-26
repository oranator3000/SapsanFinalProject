package com.ora.springfinalproject.controller;

import com.ora.springfinalproject.config.jwt.JwtProvider;
import com.ora.springfinalproject.entity.BookEntity;
import com.ora.springfinalproject.entity.OrderEntity;
import com.ora.springfinalproject.entity.UserEntity;
import com.ora.springfinalproject.entity.dto.ErrorResponse;
import com.ora.springfinalproject.entity.dto.ExceptionResponse;
import com.ora.springfinalproject.entity.dto.OrderDto;
import com.ora.springfinalproject.exceptions.BookIsTakenException;
import com.ora.springfinalproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtProvider jwtProvider;



    @GetMapping("/get-book")
    public ResponseEntity<BookEntity> get(@RequestParam Long id){
        return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/find-book")
    public ResponseEntity<List<BookEntity>> getAllByName(@RequestParam String name){
        return new ResponseEntity<>(bookService.getAllByNameContainingIgnoreCase(name), HttpStatus.OK);
    }

    @PostMapping("/create-order")
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<String> createOrder(@RequestHeader (name="Authorization") String token,@RequestBody OrderDto orderDto){
        UserEntity ue = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        OrderEntity oe = new OrderEntity();
        BookEntity bo = bookService.getById(orderDto.getBook());
        if(bo.getStatus().equals("Taken")) {
            ExceptionResponse rr = new ExceptionResponse();
            rr.setMessage("Книга не доступна");
            rr.setCode("b17");
            rr.setDate(LocalDateTime.now().toString());
            return new ResponseEntity<>("Книга не доступна",HttpStatus.BAD_REQUEST);
        }
        oe.setUser(ue);
        oe.setBook(bo);
        oe.setDateBegin(orderDto.getDateBegin());
        oe.setDateEnd(orderDto.getDateEnd());
        bo.setStatus("Taken");
        bookService.update(bo);
        orderService.createOrder(oe);
       return new ResponseEntity<>("Done",HttpStatus.OK);
    }

    @PostMapping("/show-order")
    public ResponseEntity<List<OrderEntity>> showOrders(@RequestHeader (name="Authorization") String token){
        //System.out.println("Her11");
        UserEntity ue = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        //System.out.println(ue.getId());
        return new ResponseEntity<>(orderService.getAllByUser(ue), HttpStatus.OK);
    }

    @PostMapping("/finish-order")
    public String finishOrders(@RequestParam Long id){
        orderService.finishOrder(id);
        return "Done";
    }

    @GetMapping("/show-all-book")
    public ResponseEntity<List<BookEntity>> showAllBooks(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }









}
