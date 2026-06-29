package com.pro.shipment.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Integer capacity;

    @Column(unique=true)
    private String contactNo;

    @OneToOne(mappedBy="warehouse")
    private List<Shipment> shipments;
}
