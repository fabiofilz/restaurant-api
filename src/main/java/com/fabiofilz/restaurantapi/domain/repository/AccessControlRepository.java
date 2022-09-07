package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.AccessControl;

import java.util.List;

public interface AccessControlRepository {

    List<AccessControl> getAll();
    AccessControl getById(Long id);
    AccessControl save(AccessControl accessControl);
    void delete(AccessControl accessControl);

}