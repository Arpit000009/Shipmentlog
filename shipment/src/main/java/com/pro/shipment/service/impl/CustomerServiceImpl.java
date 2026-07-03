package com.pro.shipment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.Customer;
import com.pro.shipment.repository.CustomerRepository;
import com.pro.shipment.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {

        if(customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        if(customerRepository.existsByPhone(customer.getPhone())) {
            throw new RuntimeException("Phone number already exists.");
        }

        return customerRepository.save(customer);
    }
}
