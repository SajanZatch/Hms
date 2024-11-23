package com.hms.Service;

import com.hms.entity.City;
import com.hms.entity.Country;
import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import com.hms.repository.CityRepository;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepo;
    private CityRepository cityRepo;
    private CountryRepository countryRepo;
    private ModelMapper modMap;

    public PropertyService(PropertyRepository propertyRepo, CityRepository cityRepo, CountryRepository countryRepo, ModelMapper modMap) {
        this.propertyRepo = propertyRepo;
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
        this.modMap = modMap;
    }

    public PropertyDto createProperty(PropertyDto propertyDto, Long cityId, Long countryId) {

        Country country = countryRepo.findById(countryId).get();
        propertyDto.setCountry(country);

        City city = cityRepo.findById(cityId).get();
        propertyDto.setCity(city);

        //copy to Entity
        Property property =mapToEntity(propertyDto);
        Property savedProperty =propertyRepo.save(property);


        //copy to Dto
        PropertyDto dto = mapToDto(savedProperty);

        return dto;


    }

    //dto to entity
    Property  mapToEntity(PropertyDto propertyDto){
        Property property = modMap.map(propertyDto,Property.class);
       return  property;
    }

    //entity to Dto
    PropertyDto mapToDto(Property property){
        PropertyDto dto =modMap.map(property,PropertyDto.class);
        return dto;
    }


    public List<Property> getProperty() {
        List<Property> all = propertyRepo.findAll();
        return all;

    }


    public List<Property> searchHotels(String name) {
        List<Property> properties = propertyRepo.searchHotels(name);
        return properties;
    }
}

