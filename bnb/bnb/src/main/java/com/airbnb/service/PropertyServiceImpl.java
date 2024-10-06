package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, CountryRepository countryRepository, CityRepository cityRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    City city ;
    Country  country;
    @Override
    public PropertyDto createProperty(long cityId, long countryId, PropertyDto propertyDto) {
        city =   cityRepository.findById(cityId).get();
        country = countryRepository.findById(countryId).get();

        Property property1 = mapToEntity(propertyDto);
        //property1.setCity(city);
        //property1.setCountry(country);

        Property save = propertyRepository.save(property1);
        PropertyDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<PropertyDto> listOfProperty(String cityName) {
        List<Property> byName = propertyRepository.searchProperty(cityName);
        //List<RegistrationDto> registrationdtos = content.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        List<PropertyDto> collect = byName.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return collect;
    }

    private PropertyDto mapToDto(Property entity) {
        PropertyDto dto = new PropertyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNumberOfBedroom(entity.getNumberOfBedroom());
        dto.setNumberOfBeds(entity.getNumberOfBeds());
        dto.setNumberOfBathroom(entity.getNumberOfBathroom());
        dto.setCity(entity.getCity().getId());
        dto.setCountry(entity.getCountry().getId());
        return dto;
    }

    private Property mapToEntity(PropertyDto dto) {
        Property entity = new Property();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setNumberOfBedroom(dto.getNumberOfBedroom());
        entity.setNumberOfBeds(dto.getNumberOfBeds());
        entity.setNumberOfBathroom(dto.getNumberOfBathroom());
        entity.setCity(city);
        entity.setCountry(country);
        return entity;
    }
}
