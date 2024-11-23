package com.hms.controller;

import com.hms.Service.CityService;
import com.hms.entity.City;
import com.hms.payload.CityDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService citySer;

    public CityController(CityService citySer) {
        this.citySer = citySer;
    }

    @PostMapping("/addCity")
    public ResponseEntity<?> addCity(
            @RequestBody CityDto cityDto
    ){
        CityDto dto = citySer.addCity(cityDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
   @GetMapping("/getCity")
    public ResponseEntity<List<City>> getCity(){
        List<City> city =citySer.getCity();
        return new ResponseEntity<>(city,HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteCity(
            @RequestParam long id
    ){
        citySer.deleteCity(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updateCity(
            @PathVariable long id,
            @RequestBody CityDto cityDto
    ){

        CityDto dto= citySer.updateCity(id,cityDto);

        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

}
