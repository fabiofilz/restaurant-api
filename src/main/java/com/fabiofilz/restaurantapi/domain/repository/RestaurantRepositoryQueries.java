package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
  List<Restaurant> findImplQueries(String name, BigDecimal deliveryFeeFrom, BigDecimal deliveryFeeTo);

  List<Restaurant> findWithFreeDeliveryFee(String name);
}
