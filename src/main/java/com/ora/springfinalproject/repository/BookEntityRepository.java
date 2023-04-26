package com.ora.springfinalproject.repository;

import com.ora.springfinalproject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookEntityRepository extends JpaRepository<BookEntity,Integer> {

    Optional<BookEntity> findById(Long id);

    List<BookEntity> getAllByNameContainingIgnoreCase(String name);

    List<BookEntity> findAll();
}
