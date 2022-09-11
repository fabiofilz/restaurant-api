package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

//    List<Cuisine> getByName(String name);

}