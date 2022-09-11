package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

  List<Restaurant> findByDeliveryFeeBetween(BigDecimal min, BigDecimal max);

  List<Restaurant> findByNameContainingAndCuisineId(String name, Long CuisineId);

  Optional<Restaurant> findFirstByNameContaining(String name);

  List<Restaurant> findTop2ByNameContainingOrderByNameAsc(String name);

  boolean existsByName(String name);

  int countByCuisineId(Long cuisineId);

}