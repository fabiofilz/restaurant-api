package com.fabiofilz.restaurantapi.jpa;

import com.fabiofilz.restaurantapi.RestaurantApiApplication;
import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.repository.CuisineRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SearchCuisineMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(RestaurantApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CuisineRepository cuisineRepository = applicationContext.getBean(CuisineRepository.class);

        Cuisine cuisine = cuisineRepository.getById(1L);

        System.out.println("=====> " + cuisine.getName());
    }

}