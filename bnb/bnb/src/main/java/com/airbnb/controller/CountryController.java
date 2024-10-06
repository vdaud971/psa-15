package com.airbnb.controller;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.CityDto;
import com.airbnb.payload.CountryDto;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addcountry")
    public ResponseEntity<CountryDto> addCity(@RequestBody CountryDto countryDto){

        CountryDto dto = countryService.addCountry(countryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteCountryById")
    public ResponseEntity<String>deleteCityById(@RequestParam long id){
        Optional<Country> byId = countryService.findById(id);
        if(byId.isPresent()){
            countryService.deleteById(id);
        }
        else {
            throw new UserExists("id does not exists");
        }

        return new ResponseEntity<>("record is deleted",HttpStatus.OK);

    }
}
