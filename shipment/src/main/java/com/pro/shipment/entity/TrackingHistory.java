package com.pro.shipment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TrackingHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String location;

    private String remarks;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="shipment_id")
    private Shipment shipment;
}