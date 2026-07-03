package com.pro.shipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();

        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id);

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody Customer customer) {

        Customer updatedCustomer = customerService.updateCustomer(id, customer);

        return ResponseEntity.ok(updatedCustomer);
    }

}
