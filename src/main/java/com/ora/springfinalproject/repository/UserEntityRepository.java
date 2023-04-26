package com.ora.springfinalproject.repository;

import com.ora.springfinalproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByLogin(String login);

    UserEntity getUserEntityById(Integer id);

    UserEntity getUserEntityByLogin(String login);


}
