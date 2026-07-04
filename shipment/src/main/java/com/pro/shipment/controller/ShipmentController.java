package com.pro.shipment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pro.shipment.entity.Shipment;
import com.pro.shipment.service.ShipmentService;

import jakarta.validation.Valid;

public class ShipmentController {
	
	@Autowired
	ShipmentService shipmentService;
	
	@PostMapping
	public ResponseEntity<Shipment> createShipment(
	        @Valid @RequestBody Shipment shipment){

	    Shipment savedShipment =
	            shipmentService.createShipment(shipment);

	    return new ResponseEntity<>(savedShipment,
	            HttpStatus.CREATED);
	}
}
