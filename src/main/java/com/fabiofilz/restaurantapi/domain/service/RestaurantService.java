package com.fabiofilz.restaurantapi.domain.service;

import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import com.fabiofilz.restaurantapi.domain.repository.CuisineRepository;
import com.fabiofilz.restaurantapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CuisineRepository cuisineRepository;


    public Restaurant save(Restaurant restaurant){

        Long cuisineId = restaurant.getCuisine().getId();

        Cuisine cuisine = cuisineRepository.getById(cuisineId);

        if (cuisine == null){
            throw new EntityNotFoundException(
                    String.format("CuisineId %d not found", cuisineId)
            );
        }

        restaurant.setCuisine(cuisine);
        return restaurantRepository.save(restaurant);
    }

    public void delete(Long restaurantId){
        try{
            restaurantRepository.delete(restaurantId);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("Restaurant {} not found", restaurantId)
            );
        } catch ( DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("Restaurant {} in use and cannot be deleted", restaurantId)
            );
        }


    }

}
