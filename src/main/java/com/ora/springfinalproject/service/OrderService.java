package com.ora.springfinalproject.service;

import com.ora.springfinalproject.entity.OrderEntity;
import com.ora.springfinalproject.entity.UserEntity;
import com.ora.springfinalproject.entity.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderEntity> getAllByUser(UserEntity ue);

    OrderEntity createOrder(OrderEntity oe);

    OrderEntity finishOrder(Long id);

}
