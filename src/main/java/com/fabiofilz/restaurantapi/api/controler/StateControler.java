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
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateControler {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> getAll(){
        return stateRepository.findAll();
    }

    @GetMapping(value = "/{stateId}")
    public ResponseEntity<State> getById(@PathVariable Long stateId){

        Optional<State> state = stateRepository.findById(stateId);

        if (state.isPresent()) {
            return ResponseEntity.ok(state.get());
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

        Optional<State> actualState = stateRepository.findById(stateId);

        if(actualState.isEmpty()) {
            BeanUtils.copyProperties(state, actualState.get(), "id");
            State savedState = stateService.save(actualState.get());
            return ResponseEntity.ok(savedState);
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
