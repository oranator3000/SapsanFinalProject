package com.ora.springfinalproject.controller;

import com.ora.springfinalproject.config.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/admin/get")
    public String getAdmin() {
        return "Hi admin";
    }

    @GetMapping("/user/get")
    public String getUser() {
        return "Hi user";
    }

    @GetMapping("/user/check")
    public boolean getUserCheck(@RequestHeader(name="Authorization") String token) {

        return jwtProvider.validateToken(token.substring(7));
    }

    @GetMapping("/user/check2")
    public String getNewUserCheck(@RequestHeader(name="Authorization") String token) {

        return token;
    }
}
