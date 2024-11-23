package com.hms.Service;

import com.hms.entity.City;
import com.hms.payload.CityDto;
import com.hms.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {


    private CityRepository cityRepo;

    public CityService(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    public CityDto addCity(CityDto cityDto) {
        City city =mapToEntity(cityDto);
        City savedCity = cityRepo.save(city);

        CityDto dto =mapToDto(savedCity);
        return  dto;
    }


    //dto to entity
    City mapToEntity(CityDto cityDto){
        City city =new City();
        city.setName(cityDto.getName());
        return city;
    }

    //entity to dto
    CityDto mapToDto(City city){
        CityDto cityDto = new CityDto();
        cityDto.setName(city.getName());
        return cityDto;
    }

    public List<City> getCity() {
        List<City> city = cityRepo.findAll();
        return city;
    }

    public void deleteCity(long id) {
        cityRepo.deleteById(id);
    }


    public CityDto updateCity(long id, CityDto cityDto) {
        City city=mapToEntity(cityDto);
        City c = cityRepo.findById(id).get();
        c.setName(city.getName());
        City save = cityRepo.save(c);
        CityDto dto =mapToDto(save);
        return dto;
    }
}
