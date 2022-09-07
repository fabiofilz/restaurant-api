package com.fabiofilz.restaurantapi.domain.service;

import com.fabiofilz.restaurantapi.domain.exceptions.EntityInUseException;
import com.fabiofilz.restaurantapi.domain.exceptions.EntityNotFoundException;
import com.fabiofilz.restaurantapi.domain.model.City;
import com.fabiofilz.restaurantapi.domain.model.State;
import com.fabiofilz.restaurantapi.domain.repository.CityRepository;
import com.fabiofilz.restaurantapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    StateRepository stateRepository;


    public City save(City city){

        Long stateId = city.getState().getId();

        State state = stateRepository.getById(stateId);

        if (state == null){
            throw new EntityNotFoundException(
                    String.format("StateId %d not found", stateId)
            );
        }

        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(Long cityId){
        try{
            cityRepository.delete(cityId);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("City {} not found", cityId)
            );
        } catch ( DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("City {} in use and cannot be deleted", cityId)
            );
        }
    }
}
