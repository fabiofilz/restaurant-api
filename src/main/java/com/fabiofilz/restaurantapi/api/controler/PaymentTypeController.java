package com.fabiofilz.restaurantapi.api.controler;

import com.fabiofilz.restaurantapi.domain.model.Cuisine;
import com.fabiofilz.restaurantapi.domain.model.PaymentType;
import com.fabiofilz.restaurantapi.domain.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/paymentTypes" )
public class PaymentTypeController {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    // Define the media type accepted
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PaymentType> getAll(){
        return paymentTypeRepository.findAll();
    }

    @GetMapping(value="/{paymentTypeId}")
    public ResponseEntity<PaymentType> getById(@PathVariable Long paymentTypeId){
        Optional <PaymentType> paymentType = paymentTypeRepository.findById(paymentTypeId);

        if (paymentType.isPresent()){
            return ResponseEntity.ok(paymentType.get());
        }

        return ResponseEntity.notFound().build();

    }

}
