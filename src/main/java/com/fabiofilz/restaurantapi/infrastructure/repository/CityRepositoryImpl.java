package com.fabiofilz.restaurantapi.infrastructure.repository;

import com.fabiofilz.restaurantapi.domain.model.City;
import com.fabiofilz.restaurantapi.domain.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> getAll() {
        return manager.createQuery("from City", City.class)
                .getResultList();
    }

    @Override
    public City getById(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public City save(City city) {
        return manager.merge(city);
    }

    @Transactional
    @Override
    public void delete(Long cityId) {
        City city = getById(cityId);

        if ( city == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(city);
    }
}