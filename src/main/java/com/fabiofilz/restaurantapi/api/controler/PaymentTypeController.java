package com.fabiofilz.restaurantapi.api.controler;

import com.fabiofilz.restaurantapi.domain.model.PaymentType;
import com.fabiofilz.restaurantapi.domain.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/paymentTypes" )
public class PaymentTypeController {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    // Define the media type accepted
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PaymentType> getAll(){
        return paymentTypeRepository.getAll();
    }

    @GetMapping(value="/{paymentTypeId}")
    public PaymentType getById(@PathVariable Long paymentTypeId){
        return paymentTypeRepository.getById(paymentTypeId);
    }


}
