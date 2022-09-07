package com.fabiofilz.restaurantapi.domain.repository;

import com.fabiofilz.restaurantapi.domain.model.PaymentType;

import java.util.List;

public interface PaymentTypeRepository {

    List<PaymentType> getAll();
    PaymentType getById(Long id);
    PaymentType save(PaymentType paymentType);
    void delete(PaymentType paymentType);

}