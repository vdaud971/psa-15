package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{

    private AppUserRepository appUserRepository ;
    private JWTService jwtService;
    public AppUserServiceImpl(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;

    }

    @Override
    public AppUserDto saveUserDetails(AppUserDto appUserDto) {
        AppUser appUser = mapToEntity(appUserDto);
        AppUser entity = appUserRepository.save(appUser);
       return mapToDto(entity);

    }

    @Override
    public Optional<AppUser> findByEmail(String email) {

        return appUserRepository.findByEmail(email);
    }

    @Override
    public Optional<AppUser> findByUserName(String userName) {

        return appUserRepository.findByUserName(userName);
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> byUserName = appUserRepository.findByUserName(loginDto.getUsername());
        if(byUserName.isPresent()) {
            AppUser appUser = byUserName.get();
            if(BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){

                return jwtService.generateToken(appUser);

            }
        }
        return  null;
    }

    private AppUserDto mapToDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setName(entity.getName());
        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    private AppUser mapToEntity(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserDto.getName());
        appUser.setUserName(appUserDto.getUserName());
        appUser.setEmail(appUserDto.getEmail());
        String hashpw = BCrypt.hashpw(appUserDto.getPassword(), BCrypt.gensalt(5));
        appUser.setPassword(hashpw);
        return appUser;
    }
}
