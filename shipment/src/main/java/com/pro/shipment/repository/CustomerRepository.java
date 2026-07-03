package com.pro.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	 boolean existsByEmail(String email);
	 
	 boolean existsByPhone(String phone);
}
