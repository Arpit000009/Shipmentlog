package com.pro.shipment.entity;

import java.util.List;

import com.pro.shipment.enums.AgentAvailability;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @Column(unique=true)
    private String vehicleNo;

    @Enumerated(EnumType.STRING)
    private AgentAvailability availabilityStatus;

    private Double rating;

    @OneToMany(mappedBy="deliveryAgent")
    private List<Shipment> shipments;
}
