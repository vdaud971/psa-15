package com.airbnb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/dummy")
public class Dummy {

    // Code is been modified
    @GetMapping
    public String getMessage(){

        return "welcome";

    }

}
