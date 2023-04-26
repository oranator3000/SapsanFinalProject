package com.ora.springfinalproject.entity.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Long book;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateBegin;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateEnd;
}
