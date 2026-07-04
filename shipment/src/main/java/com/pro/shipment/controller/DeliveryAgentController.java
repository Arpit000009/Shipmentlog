package com.pro.shipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.shipment.entity.DeliveryAgent;
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
}
