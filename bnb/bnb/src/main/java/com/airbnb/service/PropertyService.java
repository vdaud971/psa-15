package com.airbnb.service;

import com.airbnb.payload.PropertyDto;

import java.util.List;

public interface PropertyService {

    PropertyDto createProperty(long cityId, long countryId, PropertyDto propertyDto);

    List<PropertyDto> listOfProperty(String cityName);
}
