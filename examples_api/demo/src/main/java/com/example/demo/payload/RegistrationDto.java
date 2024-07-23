package com.example.demo.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class RegistrationDto {

    private Long id;
    @Size(min = 2,message = "name should be more than two charcter")
    private String name;

    @Email(message = "enter mail valid format")
    private String email;

    @Size(min=10,max = 10,message = "mobile number should be of 10 digits")
    private String mobile;

}