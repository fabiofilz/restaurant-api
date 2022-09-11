package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {

}