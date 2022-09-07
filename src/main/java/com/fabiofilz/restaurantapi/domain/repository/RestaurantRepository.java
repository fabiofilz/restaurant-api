package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> getAll();
    Restaurant getById(Long id);
    Restaurant save(Restaurant restaurant);
    void delete(Long restaurantId);

}