package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;

import java.util.Optional;

public interface CityService {
    CityDto addCity(CityDto cityDto);

    void deleteById(long id);

    Optional<City> findById(long id);
}
