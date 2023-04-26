package com.ora.springfinalproject.service;

import com.ora.springfinalproject.entity.BookEntity;
import com.ora.springfinalproject.entity.OrderEntity;
import com.ora.springfinalproject.entity.UserEntity;
import com.ora.springfinalproject.entity.dto.OrderDto;
import com.ora.springfinalproject.repository.BookEntityRepository;
import com.ora.springfinalproject.repository.OrderEntityRepository;
import com.ora.springfinalproject.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Autowired
    private BookEntityRepository bookEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;


    @Override
    public List<OrderEntity> getAllByUser(UserEntity ue) {
        return orderEntityRepository.findByUser(ue);
    }

    @Override
    public OrderEntity createOrder(OrderEntity oe) {
        return orderEntityRepository.save(oe);
    }

    @Override
    public OrderEntity finishOrder(Long id) {
        OrderEntity oe = orderEntityRepository.getOrderEntityById(id);
        BookEntity bo = oe.getBook();
        bo.setStatus("available");
        bookEntityRepository.save(bo);
        return null;
    }
}
