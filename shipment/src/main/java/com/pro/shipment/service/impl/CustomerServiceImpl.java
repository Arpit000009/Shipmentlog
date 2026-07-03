package com.pro.shipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.Customer;
import com.pro.shipment.exception.CustomerAlreadyExistsException;
import com.pro.shipment.exception.ResourceNotFoundException;
import com.pro.shipment.repository.CustomerRepository;
import com.pro.shipment.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {

        if(customerRepository.existsByEmail(customer.getEmail())) {
        	throw new CustomerAlreadyExistsException("Email already exists.");
        }

        if(customerRepository.existsByPhone(customer.getPhone())) {
        	throw new CustomerAlreadyExistsException("Phone number already exists.");
        }

        return customerRepository.save(customer);
    }
    
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @Override
    public Customer getCustomerById(Long id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new ResourceNotFoundException("Customer not found with id : " + id);
        }
    }
    
    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {

            Customer existingCustomer = optionalCustomer.get();

            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddress(customer.getAddress());

            return customerRepository.save(existingCustomer);
        }
        else {
            throw new ResourceNotFoundException("Customer not found with id : " + id);
        }
    }
    
    @Override
    public void deleteCustomer(Long id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {

            customerRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Customer not found with id : " + id);
        }
    }
    
    @Override
    public void deleteCustomerByPhone(String phone) {

        Customer customer = customerRepository.findByPhone(phone);

        if (customer != null) {
            customerRepository.delete(customer);
        } else {
            throw new ResourceNotFoundException("Customer not found with phone number : " + phone);
        }
    }
}
