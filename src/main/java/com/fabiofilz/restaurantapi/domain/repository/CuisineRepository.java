package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.Cuisine;

import java.util.List;

public interface CuisineRepository {

    List<Cuisine> getAll();
    List<Cuisine> getByName(String name);
    Cuisine getById(Long id);
    Cuisine save(Cuisine cuisine);
    void delete(Long cuisine);

}