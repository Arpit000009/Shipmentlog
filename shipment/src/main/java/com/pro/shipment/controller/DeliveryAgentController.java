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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.shipment.entity.DeliveryAgent;
import com.pro.shipment.enums.AgentAvailability;
import com.pro.shipment.service.DeliveryAgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/delivery-agents")
public class DeliveryAgentController {

    @Autowired
    private DeliveryAgentService deliveryAgentService;

    @PostMapping
    public ResponseEntity<DeliveryAgent> createDeliveryAgent(
            @Valid @RequestBody DeliveryAgent deliveryAgent) {

        DeliveryAgent savedAgent =
                deliveryAgentService.createDeliveryAgent(deliveryAgent);

        return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<DeliveryAgent>> getAllDeliveryAgents() {

        List<DeliveryAgent> deliveryAgents =
                deliveryAgentService.getAllDeliveryAgents();

        return new ResponseEntity(deliveryAgents,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAgent> getDeliveryAgentById(
            @PathVariable Long id) {

        DeliveryAgent deliveryAgent =
                deliveryAgentService.getDeliveryAgentById(id);

        return ResponseEntity.ok(deliveryAgent);
    }
    
    @GetMapping("/phone")
    public ResponseEntity<DeliveryAgent> getDeliveryAgentByPhone(
            @RequestParam String phone) {

        DeliveryAgent deliveryAgent =
                deliveryAgentService.getDeliveryAgentByPhone(phone);

        return ResponseEntity.ok(deliveryAgent);
    }
    
    @GetMapping("/vehicle")
    public ResponseEntity<DeliveryAgent> getDeliveryAgentByVehicleNo(
            @RequestParam String vehicleNo) {

        DeliveryAgent deliveryAgent =
                deliveryAgentService.getDeliveryAgentByVehicleNo(vehicleNo);

        return ResponseEntity.ok(deliveryAgent);
    }
    
    @GetMapping("/rating")
    public ResponseEntity<List<DeliveryAgent>> getDeliveryAgentsByRating(
            @RequestParam Double rating) {

        List<DeliveryAgent> deliveryAgents =
                deliveryAgentService.getDeliveryAgentsByRating(rating);

        return ResponseEntity.ok(deliveryAgents);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAgent> updateDeliveryAgent(
            @PathVariable Long id,
            @Valid @RequestBody DeliveryAgent deliveryAgent) {

        DeliveryAgent updatedAgent =
                deliveryAgentService.updateDeliveryAgent(id, deliveryAgent);

        return ResponseEntity.ok(updatedAgent);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryAgent(
            @PathVariable Long id) {

        deliveryAgentService.deleteDeliveryAgent(id);

        return ResponseEntity.ok("Delivery Agent deleted successfully.");
    }
    
    @PatchMapping("/{id}/availability")
    public ResponseEntity<DeliveryAgent> updateAvailability(
            @PathVariable Long id,
            @RequestParam AgentAvailability availabilityStatus) {

        DeliveryAgent deliveryAgent =
                deliveryAgentService.updateAvailability(id, availabilityStatus);

        return ResponseEntity.ok(deliveryAgent);
    }
}
