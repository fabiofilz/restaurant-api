package com.fabiofilz.restaurantapi.domain.service;

import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
import com.fabiofilz.restaurantapi.domain.model.State;
import com.fabiofilz.restaurantapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;

    public State save(State state){
        return stateRepository.save(state);
    }

    public void delete(Long stateId){

        try{
            stateRepository.delete(stateId);

        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("State {} not found", stateId)
            );
        } catch ( DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("State {} in use and cannot be deleted", stateId)
            );
        }
    }

}
