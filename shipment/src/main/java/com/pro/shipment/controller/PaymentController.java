package com.pro.shipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.shipment.entity.Payment;
import com.pro.shipment.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Payment> createPayment(
	        @RequestParam Long shipmentId,
	        @RequestBody Payment payment) {

	    Payment savedPayment =
	            paymentService.createPayment(payment, shipmentId);

	    return ResponseEntity.status(HttpStatus.CREATED)
	                         .body(savedPayment);
	}
	
	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayments() {

	    List<Payment> payments = paymentService.getAllPayments();

	    return ResponseEntity.ok(payments);
	}
}
