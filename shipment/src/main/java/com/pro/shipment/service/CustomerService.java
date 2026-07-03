package com.pro.shipment.service;

import java.util.List;

import com.pro.shipment.entity.Customer;

public interface CustomerService {
	Customer createCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(Long id);
	Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}
