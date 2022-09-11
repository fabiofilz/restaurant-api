package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

}