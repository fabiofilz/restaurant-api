package com.fabiofilz.restaurantapi.domain.service;

import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CuisineService {

    @Autowired
    CuisineRepository cuisineRepository;

    public Cuisine save(Cuisine cuisine){
        return cuisineRepository.save(cuisine);
    }

    public void delete(Long cuisineId){

        try{
        cuisineRepository.deleteById(cuisineId);

        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("Cuisine {} not found", cuisineId)
            );
        } catch ( DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("Cuisine {} in use and cannot be deleted", cuisineId)
            );
        }
    }

}
