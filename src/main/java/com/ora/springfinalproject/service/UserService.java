package com.ora.springfinalproject.service;

import com.ora.springfinalproject.entity.UserEntity;
import com.ora.springfinalproject.entity.dto.RegistrationRequest;


public interface UserService {

    //UserEntity create(UserDto userDto);

    UserEntity getById(Integer id);

    UserEntity findByLogin(String login);

    UserEntity saveUser(RegistrationRequest registrationRequest);

    UserEntity findByLoginAndPassword(String login, String password);


}
