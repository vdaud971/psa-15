package com.example.demo.controller;

import com.example.demo.payload.RegistrationDto;
import com.example.demo.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reg")
public class RegistrationController {

   private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //http://localhost:8080//api/v1/reg
    @PostMapping
    public ResponseEntity<?> getRegistration(
            @Valid @RequestBody RegistrationDto dto,
            BindingResult result
            ){
               if(result.hasErrors()){
                   return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
               }
        RegistrationDto registrationDto = registrationService.addRegistration(dto);
        return new ResponseEntity<>(registrationDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String>deleteRegistration(@RequestParam long id){
        registrationService.deleteById(id);
        return new ResponseEntity<>("record_is_deleted",HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<RegistrationDto>updateRegistration(@RequestParam long id,@RequestBody RegistrationDto registrationDto){
        RegistrationDto dto = registrationService.updateRegistration(id,registrationDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>>listOfRegistration(
            @RequestParam(name="pageNo",required = false,defaultValue="0")int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue="4")int pageSize,
            @RequestParam(name="sort",required = false,defaultValue = "name")String sort,
            @RequestParam(name="sortDir",required = false,defaultValue="asc")String sortDir
    ){
        List<RegistrationDto> registrationDtos = registrationService.listOfregistration(pageNo,pageSize,sort,sortDir);
        return new ResponseEntity<>(registrationDtos,HttpStatus.OK);

    }

    @RequestMapping("/byID")
    @GetMapping
    public ResponseEntity<RegistrationDto>findById(long id){
        RegistrationDto registrationDto = registrationService.findbyId(id);
        return new ResponseEntity<>(registrationDto,HttpStatus.OK);
    }

}
