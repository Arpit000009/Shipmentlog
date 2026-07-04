package com.pro.shipment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
