package com.fabiofilz.restaurantapi.infrastructure.spec;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestaurantWithFreeDeliveryFeeSpec implements Specification<Restaurant> {

  @Override
  public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

    return criteriaBuilder.equal(root.get("deliveryFee"), BigDecimal.ZERO);

  }
}
