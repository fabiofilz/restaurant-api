package com.fabiofilz.restaurantapi.infrastructure.repository;

import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import com.fabiofilz.restaurantapi.domain.repository.RestaurantRepository;
import com.fabiofilz.restaurantapi.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static com.fabiofilz.restaurantapi.infrastructure.spec.RestaurantSpec.*;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired @Lazy
  private RestaurantRepository restaurantRepository;

  @Override
  public List<Restaurant> findImplQueries(String name, BigDecimal deliveryFeeFrom, BigDecimal deliveryFeeTo){

    var jpql = new StringBuilder();
    jpql.append("from Restaurant where 0 = 0 ");

    var params = new HashMap<String, Object>();

    if (StringUtils.hasLength(name)) {
      jpql.append("and name like :name ");
      params.put("name", "%" + name + "%");
    }

    if (deliveryFeeFrom != null) {
      jpql.append("and deliveryFee >= :deliveryFeeFrom ");
      params.put("deliveryFeeFrom", deliveryFeeFrom);
    }

    if (deliveryFeeTo != null) {
      jpql.append("and deliveryFee <= :deliveryFeeTo ");
      params.put("deliveryFeeTo", deliveryFeeTo);
    }

    TypedQuery<Restaurant> query = entityManager
        .createQuery(jpql.toString(), Restaurant.class);

    params.forEach((key, valor) -> query.setParameter(key, valor));

    return query.getResultList();

  }

  @Override
  public List<Restaurant> findWithFreeDeliveryFee(String name) {
    return restaurantRepository.findAll(withNameLike(name).and(withFreeDeliveryFee()));
  }

}
