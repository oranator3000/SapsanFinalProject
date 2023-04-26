package com.ora.springfinalproject.service;

import com.ora.springfinalproject.entity.RoleEntity;
import com.ora.springfinalproject.entity.UserEntity;
import com.ora.springfinalproject.entity.dto.RegistrationRequest;
import com.ora.springfinalproject.repository.RoleEntityRepository;
import com.ora.springfinalproject.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setAge(registrationRequest.getAge());
        u.setName(registrationRequest.getName());
        u.setSurname(registrationRequest.getSurname());
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        u.setRoleEntity(userRole);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userEntityRepository.save(u);
    }

    @Override
    public UserEntity getById(Integer id) {
        return userEntityRepository.getUserEntityById(id);
    }

    public UserEntity findByLogin(String login) {
        //System.out.println("Her11");
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = userEntityRepository.findByLogin(login) ;//findByLogin(login);
        //System.out.println("Her10");
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
