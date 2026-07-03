package com.pro.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
