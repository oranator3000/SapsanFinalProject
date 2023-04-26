package com.ora.springfinalproject.entity.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    @Size(min = 8)
    @NotBlank(message = "password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @Max(value = 100,message = "maximum age should be 100")
    @Min(value = 18,message = "minimal age should be 18")
    private int age;
}
