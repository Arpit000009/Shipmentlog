package com.pro.shipment.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.Payment;
import com.pro.shipment.entity.Shipment;
import com.pro.shipment.exception.ResourceAlreadyExistsException;
import com.pro.shipment.exception.ResourceNotFoundException;
import com.pro.shipment.repository.PaymentRepository;
import com.pro.shipment.repository.ShipmentRepository;
import com.pro.shipment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ShipmentRepository shipmentRepository;
	
	public Payment createPayment(Payment payment, Long shipmentId) {

	    Optional<Shipment> optionalShipment =
	            shipmentRepository.findById(shipmentId);

	    if (!optionalShipment.isPresent()) {
	        throw new ResourceNotFoundException(
	                "Shipment not found.");
	    }

	    Shipment shipment = optionalShipment.get();
	    
	 // Check if payment already exists
	    if (shipment.getPayment() != null) {
	        throw new ResourceAlreadyExistsException(
	                "Payment already exists for this shipment.");
	    }
	    
	    if (paymentRepository.existsByShipmentId(shipmentId)) {
	        throw new ResourceAlreadyExistsException(
	                "Payment already exists for this shipment.");
	    }

	    payment.setShipment(shipment);

	    shipment.setPayment(payment);

	    payment.setPaymentDateTime(LocalDateTime.now());

	    return paymentRepository.save(payment);
	}
	
	@Override
	public List<Payment> getAllPayments() {

	    List<Payment> payments = paymentRepository.findAll();

	    if (payments.isEmpty()) {
	        throw new ResourceNotFoundException("No payments found.");
	    }

	    return payments;
	}
}
