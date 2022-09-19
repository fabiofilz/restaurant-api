package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CuisineRepository extends CustomJpaRepository<Cuisine, Long> {
    List<Cuisine> findByNameContaining(String name);

    @Query("from Cuisine where name =:fullname")
    List<Cuisine> searchByFullName(@Param("fullname") String name);

}