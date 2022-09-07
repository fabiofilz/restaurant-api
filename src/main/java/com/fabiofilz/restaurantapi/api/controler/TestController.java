package com.fabiofilz.restaurantapi.api.controler;

import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private CuisineRepository cuisineRepository;

  // Using Query Params - http://localhost:8080/test/cuisines/by-name?name=Thai
  @GetMapping("/cuisines/by-name")
  public List<Cuisine> getCuisineByName(@RequestParam("name") String cuisineName){
    return cuisineRepository.getByName(cuisineName);
  }

}
