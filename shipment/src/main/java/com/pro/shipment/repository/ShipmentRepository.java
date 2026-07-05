package com.pro.shipment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long>{
	boolean existsByTrackingNumber(String trackingNumber);
	Shipment findByTrackingNumber(String trackingNumber);
	List<Shipment> findByCustomerId(Long customerId);
	List<Shipment> findByDeliveryAgentId(Long deliveryAgentId);
	List<Shipment> findBySourceIgnoreCase(String source);
	List<Shipment> findByDestinationIgnoreCase(String destination);
	List<Shipment> findByExpectedDeliveryDate(LocalDate expectedDeliveryDate);
}
