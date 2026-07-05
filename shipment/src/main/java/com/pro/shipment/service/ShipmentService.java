package com.pro.shipment.service;

import java.util.List;

import com.pro.shipment.entity.Shipment;
import com.pro.shipment.enums.ShipmentStatus;

public interface ShipmentService {
	Shipment createShipment(Shipment shipment);
	List<Shipment> getAllShipments();
	Shipment getShipmentById(Long id);
	Shipment getShipmentByTrackingNumber(String trackingNumber);
	Shipment updateShipmentStatus(Long id,ShipmentStatus status);
	Shipment assignDeliveryAgent(Long shipmentId, Long deliveryAgentId);
	Shipment assignWarehouse(Long shipmentId, Long warehouseId);
	List<Shipment> getShipmentsByCustomer(Long customerId);
	void deleteShipment(Long id);
	List<Shipment> getShipmentsByDeliveryAgent(Long deliveryAgentId);
	List<Shipment> getShipmentsBySource(String source);
	List<Shipment> getShipmentsByDestination(String destination);
	
}
