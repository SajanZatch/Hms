package com.hms.controller;

import com.hms.Service.CountryService;
import com.hms.entity.AppUser;
import com.hms.entity.Country;
import com.hms.payload.CountryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

     private CountryService countrySer;

    public CountryController(CountryService countrySer) {
        this.countrySer = countrySer;
    }

    // http://localhost:8080/api/v1/country
    @PostMapping("/addCountry")
    public AppUser addCountry(
            //with below annotation we can know which user currently logged in
            @AuthenticationPrincipal AppUser user
            ){

        return user;
    }

    @PostMapping("/insertCountry")
    public ResponseEntity<?> createCountry(
            @RequestBody CountryDto countryDto
    ){
        CountryDto dto =countrySer.createCountry(countryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @GetMapping("/getCountry")
    public ResponseEntity <List<Country>> getCountry(){
        List<Country> country = countrySer.getCountry();
        return new ResponseEntity<>(country,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCountry(
            @RequestParam long id
    ){
        countrySer.deleteCountry(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> updateCountry(
            @PathVariable long id,
            @RequestBody CountryDto countryDto
    ){
       CountryDto dto = countrySer.updateCountry(id,countryDto);
       return new ResponseEntity<>(dto,HttpStatus.OK);

    }


}
