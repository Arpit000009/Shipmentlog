package com.pro.shipment.service;

import java.util.List;

import com.pro.shipment.entity.Shipment;

public interface ShipmentService {
	Shipment createShipment(Shipment shipment);
	List<Shipment> getAllShipments();
}
