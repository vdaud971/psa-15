package com.example.demo.service;

import com.example.demo.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    RegistrationDto addRegistration(RegistrationDto dto);

    void deleteById(long id);

    RegistrationDto updateRegistration(long id, RegistrationDto registrationDto);

    List<RegistrationDto> listOfregistration(int pageNo, int pageSize, String sort, String sortDir);

    RegistrationDto findbyId(long id);
}
