package com.airbnb.service;


import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.payload.CityDto;
import com.airbnb.payload.CountryDto;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{

    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {

        Country country = mapToEntity(countryDto);
        return mapToDto(countryRepository.save(country));

    }

    @Override
    public Optional<Country> findById(long id) {
        return countryRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        countryRepository.deleteById(id);
    }

    private Country mapToEntity(CountryDto countryDto) {
        Country country = new Country();
        country.setId(countryDto.getId());
        country.setName(countryDto.getName());
        return country;
    }

    private CountryDto mapToDto(Country country) {
        CountryDto dto = new CountryDto();
            dto.setId(country.getId());
            dto.setName(country.getName());
            return dto;
    }

}
