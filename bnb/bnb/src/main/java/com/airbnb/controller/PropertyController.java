package com.airbnb.controller;

import com.airbnb.payload.PropertyDto;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/add")
    public ResponseEntity<PropertyDto>createProperty(@RequestParam long cityId,
                                                     @RequestParam long countryId,
                                                     @RequestBody PropertyDto propertyDto
                                                     ){
        PropertyDto propertyDtoo = propertyService.createProperty(cityId, countryId, propertyDto);

        return new ResponseEntity<>(propertyDtoo, HttpStatus.CREATED);

    }

    @GetMapping("/propertyresult")
    public ResponseEntity<List<PropertyDto>> searchProperty(
            @RequestParam("name") String cityName
    ){
        List<PropertyDto> propertyDtos = propertyService.listOfProperty(cityName);
        return new ResponseEntity<>(propertyDtos,HttpStatus.OK);
    }
}
