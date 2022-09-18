package com.fabiofilz.restaurantapi.infrastructure.spec;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpec {

  public static Specification<Restaurant> withFreeDeliveryFee(){
    return (root, query, builder) -> builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
  }

  public static Specification<Restaurant> withNameLike(String name){
    return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
  }

}
