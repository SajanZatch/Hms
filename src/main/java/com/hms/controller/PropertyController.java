package com.hms.controller;


import com.hms.Service.PropertyService;
import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService propertySer;

    public PropertyController(PropertyService propertySer) {
        this.propertySer = propertySer;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> createProperty(

            @RequestParam Long cityId,
            @RequestParam Long countryId,
            @RequestBody PropertyDto propertyDto
    ){
        PropertyDto dto = propertySer.createProperty(propertyDto,cityId,countryId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("/getProperty")
    public ResponseEntity <List<Property>> getProperty(){

       List<Property> properties= propertySer.getProperty();

       return new ResponseEntity<>(properties,HttpStatus.OK);
    }
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteProperty(
//            @RequestParam long cityId,
//            @RequestParam long countryId
//    ){
//        propertySer.deletePropertyByCityAndCountry(cityId,countryId);
//        return new ResponseEntity<>("deleted",HttpStatus.OK);
//
//    }

    @GetMapping("/search-hotels")
    public List<Property> searchHotels(
            @RequestParam String name
    ){
        List<Property> properties =propertySer.searchHotels(name);
        return properties;
    }
}
