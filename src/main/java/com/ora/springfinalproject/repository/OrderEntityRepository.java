package com.ora.springfinalproject.repository;

import com.ora.springfinalproject.entity.OrderEntity;
import com.ora.springfinalproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByUser(UserEntity ue);

    OrderEntity getOrderEntityById(Long id);

}
