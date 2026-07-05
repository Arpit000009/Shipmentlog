package com.pro.shipment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pro.shipment.entity.Payment;

@Service
public interface PaymentService {
	public Payment createPayment(Payment payment, Long shipmentId);
	List<Payment> getAllPayments();
}
