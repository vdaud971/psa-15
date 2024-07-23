package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;

import java.util.Optional;

public interface AppUserService {

    AppUserDto saveUserDetails(AppUserDto appUserDto);

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByUserName(String userName);

    String verifyLogin(LoginDto loginDto);
}
