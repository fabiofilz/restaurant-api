package com.fabiofilz.restaurantapi.infrastructure.repository;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import com.fabiofilz.restaurantapi.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Restaurant> findImplQueries(String name, BigDecimal deliveryFeeFrom, BigDecimal deliveryFeeTo){

    var jpql = "from Restaurant where name like :name "
              + "and deliveryFee between :deliveryFeeFrom and :deliveryFeeTo";

    return entityManager.createQuery(jpql, Restaurant.class)
        .setParameter("name", "%" + name + "%")
        .setParameter("deliveryFeeFrom", deliveryFeeFrom)
        .setParameter("deliveryFeeTo", deliveryFeeTo)
        .getResultList();

  }

}
