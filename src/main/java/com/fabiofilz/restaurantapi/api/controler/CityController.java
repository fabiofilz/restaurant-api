package com.fabiofilz.restaurantapi.api.controler;

import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
import com.fabiofilz.restaurantapi.domain.model.City;
import com.fabiofilz.restaurantapi.domain.repository.CityRepository;
import com.fabiofilz.restaurantapi.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getAll(){
        return cityRepository.findAll();
    }

    @GetMapping(value="/{cityId}")
    public ResponseEntity<City> getById (@PathVariable Long cityId){

        Optional<City> city = cityRepository.findById(cityId);

        if (city.isPresent()){
            return ResponseEntity.ok(city.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> adding(@RequestBody City city){
        try{
            city = cityService.save(city);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntityNotFoundException e){
            System.out.println("");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<City> update(@PathVariable Long cityId,
                                       @RequestBody City city){

        Optional<City> actualCity = cityRepository.findById(cityId);
        if(actualCity.isEmpty()) {
            BeanUtils.copyProperties(city, actualCity.get(), "id");
            City savedCity = cityService.save(actualCity.get());
            return ResponseEntity.ok(savedCity);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<City> delete(@PathVariable Long cityId){
        try {
            cityService.delete(cityId);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
