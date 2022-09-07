package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> getAll();
    City getById(Long id);
    City save(City city);
    void delete(Long cityId);

}