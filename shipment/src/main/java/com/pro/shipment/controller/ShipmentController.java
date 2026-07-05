package com.pro.shipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.shipment.entity.Shipment;
import com.pro.shipment.enums.ShipmentStatus;
import com.pro.shipment.service.ShipmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shipments")
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
	
	@GetMapping
	public ResponseEntity<List<Shipment>> getAllShipments() {

	    List<Shipment> shipments = shipmentService.getAllShipments();

	    return ResponseEntity.ok(shipments);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Shipment> getShipmentById(
	        @PathVariable Long id) {

	    Shipment shipment = shipmentService.getShipmentById(id);

	    return ResponseEntity.ok(shipment);
	}
	
	@GetMapping("/tracking")
	public ResponseEntity<Shipment> getShipmentByTrackingNumber(
	        @RequestParam String trackingNumber) {

	    Shipment shipment =
	            shipmentService.getShipmentByTrackingNumber(trackingNumber);

	    return ResponseEntity.ok(shipment);
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<Shipment> updateShipmentStatus(
	        @PathVariable Long id,
	        @RequestParam ShipmentStatus status) {

	    Shipment shipment =
	            shipmentService.updateShipmentStatus(id, status);

	    return ResponseEntity.ok(shipment);
	}
	
	@PatchMapping("/{shipmentId}/assign-agent")
	public ResponseEntity<Shipment> assignDeliveryAgent(
	        @PathVariable Long shipmentId,
	        @RequestParam Long deliveryAgentId) {

	    Shipment shipment =
	            shipmentService.assignDeliveryAgent(shipmentId, deliveryAgentId);

	    return ResponseEntity.ok(shipment);
	}
	
	@PatchMapping("/{shipmentId}/assign-warehouse")
	public ResponseEntity<Shipment> assignWarehouse(
	        @PathVariable Long shipmentId,
	        @RequestParam Long warehouseId) {

	    Shipment shipment =
	            shipmentService.assignWarehouse(shipmentId, warehouseId);

	    return ResponseEntity.ok(shipment);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Shipment>> getShipmentsByCustomer(
	        @PathVariable Long customerId) {

	    List<Shipment> shipments =
	            shipmentService.getShipmentsByCustomer(customerId);

	    return ResponseEntity.ok(shipments);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShipment(@PathVariable Long id) {

	    shipmentService.deleteShipment(id);

	    return ResponseEntity.ok("Shipment deleted successfully.");
	}
	
	@GetMapping("/delivery-agent/{deliveryAgentId}")
	public ResponseEntity<List<Shipment>> getShipmentsByDeliveryAgent(
	        @PathVariable Long deliveryAgentId) {

	    List<Shipment> shipments =
	            shipmentService.getShipmentsByDeliveryAgent(deliveryAgentId);

	    return ResponseEntity.ok(shipments);
	}
	
	@GetMapping("/source")
	public ResponseEntity<List<Shipment>> getShipmentsBySource(
	        @RequestParam String source) {

	    List<Shipment> shipments =
	            shipmentService.getShipmentsBySource(source);

	    return ResponseEntity.ok(shipments);
	}
	
	@GetMapping("/destination")
	public ResponseEntity<List<Shipment>> getShipmentsByDestination(
	        @RequestParam String destination) {

	    List<Shipment> shipments =
	            shipmentService.getShipmentsByDestination(destination);

	    return ResponseEntity.ok(shipments);
	}
}
