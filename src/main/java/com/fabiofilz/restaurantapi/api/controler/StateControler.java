package com.fabiofilz.restaurantapi.api.controler;

import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
import com.fabiofilz.restaurantapi.domain.model.State;
import com.fabiofilz.restaurantapi.domain.repository.StateRepository;
import com.fabiofilz.restaurantapi.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateControler {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> getAll(){
        return stateRepository.getAll();
    }

    @GetMapping(value = "/{stateId}")
    public ResponseEntity<State> getById(@PathVariable Long stateId){

        State state = stateRepository.getById(stateId);

        if (state != null) {
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State adding(@RequestBody State state) {

        return stateService.save(state);
    }


    @PutMapping(value = "/{stateId}")
    public ResponseEntity<State> update(@PathVariable Long stateId,
                                          @RequestBody State state){

        State actualState = stateRepository.getById(stateId);
        if(actualState != null) {
            BeanUtils.copyProperties(state, actualState, "id");
            return ResponseEntity.ok(stateService.save(actualState));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping(value = "/{stateId}")
    public ResponseEntity<State> delete(@PathVariable Long stateId){
        try{
            stateService.delete(stateId);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
