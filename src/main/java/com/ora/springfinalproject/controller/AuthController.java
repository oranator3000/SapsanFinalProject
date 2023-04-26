package com.ora.springfinalproject.controller;

import com.ora.springfinalproject.config.jwt.JwtProvider;
import com.ora.springfinalproject.entity.UserEntity;
import com.ora.springfinalproject.entity.dto.AuthRequest;
import com.ora.springfinalproject.entity.dto.RegistrationRequest;
import com.ora.springfinalproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        userService.saveUser(registrationRequest);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        //System.out.println("Her1");
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        //System.out.println("Her2");
        String token = jwtProvider.generateToken(userEntity.getLogin());
       // System.out.println("Her3");
        return new AuthResponse(token);
    }
}
