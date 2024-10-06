package com.airbnb.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AppUserDto {


    private Long id;
    private String name;
    private String email;
    private String userName;
    private String password;


}