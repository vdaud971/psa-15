package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import com.airbnb.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto addCity(CityDto cityDto) {
        City city = mapToEntity(cityDto);
       return mapToDto(cityRepository.save(city));
    }

    @Override
    public void deleteById(long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public Optional<City> findById(long id) {
       return cityRepository.findById(id);
    }

    private CityDto mapToDto(City city) {
        CityDto dto = new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }

    private City mapToEntity(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
      city.setName(cityDto.getName());
      return city;
    }
}
