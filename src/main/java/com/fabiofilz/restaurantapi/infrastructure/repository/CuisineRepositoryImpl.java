package com.fabiofilz.restaurantapi.infrastructure.repository;

import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.repository.CuisineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CuisineRepositoryImpl implements CuisineRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cuisine> getAll() {
        return manager.createQuery("from Cuisine", Cuisine.class)
                .getResultList();
    }

    // Using JPQL
    @Override
    public List<Cuisine> getByName(String name) {
        return manager.createQuery("from Cuisine where name like :name", Cuisine.class)
            .setParameter("name", "%" + name + "%")
            .getResultList();
    }

    @Override
    public Cuisine getById(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Transactional
    @Override
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

    @Transactional
    @Override
    public void delete(Long cuisineId) {
        Cuisine cuisine = getById(cuisineId);

        if ( cuisine == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cuisine);
    }
}