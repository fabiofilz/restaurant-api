    package com.fabiofilz.restaurantapi.api.controler;

    import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
    import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
    import com.fabiofilz.restaurantapi.domain.model.Restaurant;
    import com.fabiofilz.restaurantapi.domain.repository.RestaurantRepository;
    import com.fabiofilz.restaurantapi.domain.service.RestaurantService;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.beans.BeanUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.util.ReflectionUtils;
    import org.springframework.web.bind.annotation.*;

    import java.lang.reflect.Field;
    import java.util.List;
    import java.util.Map;

    @RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll(){

        return restaurantRepository.getAll();

    }

    @GetMapping(value="/{restaurantId}")
    public ResponseEntity<Restaurant> getById (@PathVariable Long restaurantId){

        Restaurant restaurant = restaurantRepository.getById(restaurantId);

        if (restaurant != null){
            return ResponseEntity.ok(restaurant);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> adding(@RequestBody Restaurant restaurant){
        try{
            restaurant = restaurantService.save(restaurant);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(restaurant);
        } catch (EntityNotFoundException e){
            System.out.println("");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{restaurantId}")
    public ResponseEntity<Restaurant> update(@PathVariable Long restaurantId,
                                          @RequestBody Restaurant restaurant){

        Restaurant actualRestaurant = restaurantRepository.getById(restaurantId);
        if(actualRestaurant != null) {
            BeanUtils.copyProperties(restaurant, actualRestaurant, "id");
            return ResponseEntity.ok(restaurantService.save(actualRestaurant));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{restaurantId}")
    public ResponseEntity<Restaurant> delete(@PathVariable Long restaurantId){
        try {
            restaurantService.delete(restaurantId);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> parcialUpdate(@PathVariable Long restaurantId,
                                              @RequestBody Map<String, Object> campos) {

        Restaurant atualRestaurant = restaurantRepository.getById(restaurantId);

        if (atualRestaurant == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, atualRestaurant);

        return update(restaurantId, atualRestaurant);
    }

    private void merge(Map<String, Object> source, Restaurant target) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantSource = objectMapper.convertValue(source, Restaurant.class);

        source.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restaurantSource);

            ReflectionUtils.setField(field, target, newValue);
        });
    }
}
