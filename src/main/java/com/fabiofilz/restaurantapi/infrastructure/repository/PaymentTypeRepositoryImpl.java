package com.fabiofilz.restaurantapi.infrastructure.repository;

import com.fabiofilz.restaurantapi.domain.model.PaymentType;
import com.fabiofilz.restaurantapi.domain.repository.PaymentTypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PaymentTypeRepositoryImpl implements PaymentTypeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentType> getAll() {
        return manager.createQuery("from PaymentType", PaymentType.class)
                .getResultList();
    }

    @Override
    public PaymentType getById(Long id) {
        return manager.find(PaymentType.class, id);
    }

    @Transactional
    @Override
    public PaymentType save(PaymentType paymentType) {
        return manager.merge(paymentType);
    }

    @Transactional
    @Override
    public void delete(PaymentType paymentType) {
        paymentType = getById(paymentType.getId());
        manager.remove(paymentType);
    }

}