package com.pro.shipment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.shipment.entity.Customer;
import com.pro.shipment.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	
	@Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer( @Valid @RequestBody Customer customer) {

        Customer savedCustomer = customerService.createCustomer(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

}
