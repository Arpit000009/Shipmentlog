package com.pro.shipment.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.pro.shipment.entity.Customer;
import com.pro.shipment.entity.DeliveryAgent;
import com.pro.shipment.entity.Shipment;
import com.pro.shipment.entity.Warehouse;
import com.pro.shipment.enums.ShipmentStatus;
import com.pro.shipment.exception.ResourceAlreadyExistsException;
import com.pro.shipment.exception.ResourceNotFoundException;
import com.pro.shipment.repository.CustomerRepository;
import com.pro.shipment.repository.DeliveryAgentRepository;
import com.pro.shipment.repository.ShipmentRepository;
import com.pro.shipment.repository.TrackingHistoryRepository;
import com.pro.shipment.repository.WarehouseRepository;
import com.pro.shipment.service.ShipmentService;

public class ShipmentServiceImpl implements ShipmentService{
	
	@Autowired
	ShipmentRepository shipmentRepository;
	
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Autowired
    private TrackingHistoryRepository trackingHistoryRepository;

//    @Autowired
//    private PackageRepository packageRepository;
	
	@Override
	public Shipment createShipment(Shipment shipment)  {

	    if(shipmentRepository.existsByTrackingNumber(
	            shipment.getTrackingNumber())) {

	        throw new ResourceAlreadyExistsException(
	                "Tracking Number already exists.");
	    }

	    Customer customer =
	            customerRepository.findById(
	                    shipment.getCustomer().getId())
	                    .orElse(null);

	    if(customer == null)
	        throw new ResourceNotFoundException("Customer not found.");



	    Warehouse warehouse =
	            warehouseRepository.findById(
	                    shipment.getWarehouse().getId())
	                    .orElse(null);

	    if(warehouse == null)
	        throw new ResourceNotFoundException("Warehouse not found.");



	    DeliveryAgent agent =
	            deliveryAgentRepository.findById(
	                    shipment.getDeliveryAgent().getId())
	                    .orElse(null);

	    if(agent == null)
	        throw new ResourceNotFoundException("Delivery Agent not found.");



	    shipment.setCustomer(customer);

	    shipment.setWarehouse(warehouse);

	    shipment.setDeliveryAgent(agent);

	    shipment.setShipmentDateTime(LocalDateTime.now());

	    shipment.setStatus(ShipmentStatus.CREATED);

	    return shipmentRepository.save(shipment);

	}
}
