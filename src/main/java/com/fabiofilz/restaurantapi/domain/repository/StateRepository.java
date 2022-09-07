package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> getAll();
    State getById(Long id);
    State save(State state);
    void delete(Long stateId);

}