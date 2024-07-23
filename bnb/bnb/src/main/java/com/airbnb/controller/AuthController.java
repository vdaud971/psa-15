package com.airbnb.controller;


import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.JWTToken;
import com.airbnb.payload.LoginDto;
import com.airbnb.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


   private AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<AppUserDto>saveUserDetails(@RequestBody AppUserDto appUserDto){
        Optional<AppUser> optEmail = appUserService.findByEmail(appUserDto.getEmail());
           if(optEmail.isPresent())
               throw new UserExists("Email Id exists");

        Optional<AppUser> optUserName = appUserService.findByUserName(appUserDto.getUserName());
        if(optUserName.isPresent())
            throw new UserExists("UserName  exists");

        AppUserDto savedUser = appUserService.saveUserDetails(appUserDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?>signIn(@RequestBody LoginDto loginDto ){
        String token = appUserService.verifyLogin(loginDto);
        JWTToken jwtToken = new JWTToken();
        if(token!=null){
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid_user_name_password",HttpStatus.UNAUTHORIZED);
        }
    }

}
