package com.fabiofilz.restaurantapi.infrastructure.spec;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class RestaurantWithNameLikeSpec implements Specification<Restaurant> {

  private String name;

  @Override
  public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.like(root.get("name"), "%" + name + "%");
  }
}