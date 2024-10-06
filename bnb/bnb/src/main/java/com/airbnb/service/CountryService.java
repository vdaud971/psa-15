package com.airbnb.service;


import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.payload.CityDto;
import com.airbnb.payload.CountryDto;

import java.util.Optional;

public interface CountryService {
    CountryDto addCountry(CountryDto countryDto);

    Optional<Country> findById(long id);

    void deleteById(long id);
}
