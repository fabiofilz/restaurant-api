package com.fabiofilz.restaurantapi.api.controler;

import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.repository.CuisineRepository;
import com.fabiofilz.restaurantapi.domain.service.CuisineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineService cuisineService;

    @GetMapping
    public List<Cuisine> getAll() {
        return cuisineRepository.findAll();
    }

    @GetMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> getById(@PathVariable Long cuisineId){

        Optional<Cuisine> cuisine = cuisineRepository.findById(cuisineId);

        if (cuisine.isPresent()) {
            return ResponseEntity.ok(cuisine.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine adding(@RequestBody Cuisine cuisine) {

        return cuisineService.save(cuisine);
    }


    @PutMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> update(@PathVariable Long cuisineId,
                                          @RequestBody Cuisine cuisine){

        Optional<Cuisine> actualCuisine = cuisineRepository.findById(cuisineId);
        if(actualCuisine.isPresent()) {
            BeanUtils.copyProperties(cuisine, actualCuisine.get(), "id");
            Cuisine savedCuisine = cuisineService.save(actualCuisine.get());
            return ResponseEntity.ok(savedCuisine);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> delete(@PathVariable Long cuisineId){
        try{
            cuisineService.delete(cuisineId);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
