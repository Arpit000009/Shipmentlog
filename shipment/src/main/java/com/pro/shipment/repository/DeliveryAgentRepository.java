package com.pro.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long>{

}
