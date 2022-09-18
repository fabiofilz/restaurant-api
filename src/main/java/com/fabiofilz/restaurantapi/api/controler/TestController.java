package com.fabiofilz.restaurantapi.api.controler;

import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.model.Restaurant;
import com.fabiofilz.restaurantapi.domain.repository.CuisineRepository;
import com.fabiofilz.restaurantapi.domain.repository.RestaurantRepository;
import com.fabiofilz.restaurantapi.infrastructure.spec.RestaurantWithFreeDeliveryFeeSpec;
import com.fabiofilz.restaurantapi.infrastructure.spec.RestaurantWithNameLikeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private CuisineRepository cuisineRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

//   Using Query Params - http://localhost:8080/test/cuisines/by-name?name=Thai
  @GetMapping("/cuisines/by-name")
  public List<Cuisine> getCuisineByName(@RequestParam("name") String cuisineName){
    return cuisineRepository.findByNameContaining(cuisineName);
  }

  @GetMapping("/restaurants/by-delivery-fee")
  public List<Restaurant> getRestaurantByDeliveryFee(BigDecimal min, BigDecimal max){
    return restaurantRepository.findByDeliveryFeeBetween(min, max);
  }

  @GetMapping("/restaurants/by-name-cuisine")
  public List<Restaurant> getByNameContainingAndCuisineId(String name, Long cuisineId){
    return restaurantRepository.findByNameContainingAndCuisineId(name, cuisineId);
  }

  @GetMapping("/restaurants/first-by-name")
  public Optional<Restaurant> getFirstByNameContaining(@RequestParam("name") String name){
    return restaurantRepository.findFirstByNameContaining(name);
  }

  @GetMapping("/restaurants/top2-by-name")
  public List<Restaurant> getTop2ByNameContainingOrderByNameAsc(@RequestParam("name") String name){
    return restaurantRepository.findTop2ByNameContainingOrderByNameAsc(name);
  }

  @GetMapping("/restaurants/exists-by-name")
  public Boolean getExistsByName(@RequestParam("name") String name){
    return restaurantRepository.existsByName(name);
  }

  @GetMapping("/restaurants/count-by-cuisine")
  public int getcountByCuisineId(@RequestParam("cuisineId") Long cuisineId){
    return restaurantRepository.countByCuisineId(cuisineId);
  }

  @GetMapping("/cuisines/by-fullname")
  public List<Cuisine> getCuisineByFullName(@RequestParam("name") String cuisineName){
    return cuisineRepository.searchByFullName(cuisineName);
  }

  @GetMapping("/restaurants/by-name-and-deliveryfee")
  public List<Restaurant> restaurantsByNameAndDeliveryFee(String name, BigDecimal deliveryFeeFrom, BigDecimal deliveryFeeTo){
    return restaurantRepository.findImplQueries(name, deliveryFeeFrom, deliveryFeeTo);
  }

  @GetMapping("/restaurants/free-delivery-fee")
  public List<Restaurant> findRestaurantFreeDeliveryFee(String name){
    var withFreeDeliveryFee = new RestaurantWithFreeDeliveryFeeSpec();
    var withNameLike = new RestaurantWithNameLikeSpec(name);

    return restaurantRepository.findAll(withFreeDeliveryFee.and(withNameLike));
  }
}
