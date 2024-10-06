package com.airbnb.controller;


import com.airbnb.entity.City;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.CityDto;
import com.airbnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService cityService ;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/addcity")
    public ResponseEntity<CityDto>addCity(@RequestBody CityDto cityDto){

        CityDto dto = cityService.addCity(cityDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @DeleteMapping("/deletecityById")
    public ResponseEntity<String>deleteCityById(@RequestParam long id){
        Optional<City> byId = cityService.findById(id);
                    if(byId.isPresent()){
                        cityService.deleteById(id);
                    }
                    else {
                        throw new UserExists("id does not exists");
                    }

        return new ResponseEntity<>("record is deleted",HttpStatus.OK);
         
    }
}
