package com.fabiofilz.restaurantapi.infrastructure.repository;

import com.fabiofilz.restaurantapi.domain.model.AccessControl;
import com.fabiofilz.restaurantapi.domain.repository.AccessControlRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AccessControlRepositoryImpl implements AccessControlRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<AccessControl> getAll() {
        return manager.createQuery("from AccessControl", AccessControl.class)
                .getResultList();
    }

    @Override
    public AccessControl getById(Long id) {
        return manager.find(AccessControl.class, id);
    }

    @Transactional
    @Override
    public AccessControl save(AccessControl accessControl) {
        return manager.merge(accessControl);
    }

    @Transactional
    @Override
    public void delete(AccessControl accessControl) {
        accessControl = getById(accessControl.getId());
        manager.remove(accessControl);
    }

}