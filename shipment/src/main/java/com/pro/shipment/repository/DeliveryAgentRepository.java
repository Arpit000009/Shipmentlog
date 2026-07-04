package com.pro.shipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long>{
	boolean existsByPhone(String phone);

    boolean existsByVehicleNo(String vehicleNo);
    
    DeliveryAgent findByPhone(String phone);
    
    DeliveryAgent findByVehicleNo(String vehicleNo);
    
    List<DeliveryAgent> findByRatingGreaterThan(Double rating);
}
