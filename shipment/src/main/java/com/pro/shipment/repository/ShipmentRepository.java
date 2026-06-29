package com.pro.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long>{

}
