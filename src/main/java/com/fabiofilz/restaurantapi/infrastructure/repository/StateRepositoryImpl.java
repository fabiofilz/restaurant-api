package com.fabiofilz.restaurantapi.infrastructure.repository;

import com.fabiofilz.restaurantapi.domain.model.State;
import com.fabiofilz.restaurantapi.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> getAll() {
        return manager.createQuery("from State", State.class)
                .getResultList();
    }

    @Override
    public State getById(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State save(State state) {
        return manager.merge(state);
    }

    @Transactional
    @Override
    public void delete(Long stateId) {
        State state = getById(stateId);

        if ( state == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(state);
    }

}