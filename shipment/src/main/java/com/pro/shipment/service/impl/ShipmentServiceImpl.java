package com.pro.shipment.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.Customer;
import com.pro.shipment.entity.DeliveryAgent;
import com.pro.shipment.entity.PackageEntity;
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

@Service
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

	    PackageEntity packageEntity = shipment.getPackageEntity();

	    if (packageEntity != null) {
	        packageEntity.setShipment(shipment);
	    }
	    
	    return shipmentRepository.save(shipment);

	}
	
	@Override
	public List<Shipment> getAllShipments() {

	    List<Shipment> shipments = shipmentRepository.findAll();

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException("No shipments found.");
	    }

	    return shipments;
	}
	
	@Override
	public Shipment getShipmentById(Long id) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findById(id);

	    if (optionalShipment.isPresent()) {

	        return optionalShipment.get();

	    } else {

	        throw new ResourceNotFoundException(
	                "Shipment not found with id : " + id);
	    }
	}
	
	@Override
	public Shipment getShipmentByTrackingNumber(String trackingNumber) {

	    Shipment shipment =
	            shipmentRepository.findByTrackingNumber(trackingNumber);

	    if (shipment != null) {

	        return shipment;

	    } else {

	        throw new ResourceNotFoundException(
	                "Shipment not found with tracking number : "
	                        + trackingNumber);
	    }
	}
	
	@Override
	public Shipment updateShipmentStatus(Long id,
	                                     ShipmentStatus status) {

	    Optional<Shipment> optionalShipment =
	            shipmentRepository.findById(id);

	    if (optionalShipment.isPresent()) {

	        Shipment shipment = optionalShipment.get();

	        shipment.setStatus(status);

	        return shipmentRepository.save(shipment);

	    } else {

	        throw new ResourceNotFoundException(
	                "Shipment not found with id : " + id);
	    }
	}
	
	@Override
	public Shipment assignDeliveryAgent(Long shipmentId, Long deliveryAgentId) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findById(shipmentId);

	    if (!optionalShipment.isPresent()) {
	        throw new ResourceNotFoundException(
	                "Shipment not found with id : " + shipmentId);
	    }

	    Optional<DeliveryAgent> optionalAgent =
	            deliveryAgentRepository.findById(deliveryAgentId);

	    if (!optionalAgent.isPresent()) {
	        throw new ResourceNotFoundException(
	                "Delivery Agent not found with id : " + deliveryAgentId);
	    }

	    Shipment shipment = optionalShipment.get();
	    shipment.setDeliveryAgent(optionalAgent.get());

	    return shipmentRepository.save(shipment);
	}
	
	@Override
	public Shipment assignWarehouse(Long shipmentId, Long warehouseId) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findById(shipmentId);

	    if (!optionalShipment.isPresent()) {
	        throw new ResourceNotFoundException(
	                "Shipment not found with id : " + shipmentId);
	    }

	    Optional<Warehouse> optionalWarehouse =
	            warehouseRepository.findById(warehouseId);

	    if (!optionalWarehouse.isPresent()) {
	        throw new ResourceNotFoundException(
	                "Warehouse not found with id : " + warehouseId);
	    }

	    Shipment shipment = optionalShipment.get();
	    shipment.setWarehouse(optionalWarehouse.get());

	    return shipmentRepository.save(shipment);
	}
	
	@Override
	public List<Shipment> getShipmentsByCustomer(Long customerId) {

	    List<Shipment> shipments =
	            shipmentRepository.findByCustomerId(customerId);

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException(
	                "No shipments found for customer id : " + customerId);
	    }

	    return shipments;
	}
	
	@Override
	public void deleteShipment(Long id) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findById(id);

	    if (optionalShipment.isPresent()) {

	        shipmentRepository.deleteById(id);

	    } else {

	        throw new ResourceNotFoundException(
	                "Shipment not found with id : " + id);
	    }
	}
	
	@Override
	public List<Shipment> getShipmentsByDeliveryAgent(Long deliveryAgentId) {

	    List<Shipment> shipments =
	            shipmentRepository.findByDeliveryAgentId(deliveryAgentId);

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException(
	                "No shipments found for delivery agent id : " + deliveryAgentId);
	    }

	    return shipments;
	}
	
	@Override
	public List<Shipment> getShipmentsBySource(String source) {

	    List<Shipment> shipments =
	            shipmentRepository.findBySourceIgnoreCase(source);

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException(
	                "No shipments found from source : " + source);
	    }

	    return shipments;
	}
	
	@Override
	public List<Shipment> getShipmentsByDestination(String destination) {

	    List<Shipment> shipments =
	            shipmentRepository.findByDestinationIgnoreCase(destination);

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException(
	                "No shipments found for destination : " + destination);
	    }

	    return shipments;
	}
	
	@Override
	public List<Shipment> getShipmentsByDeliveryDate(LocalDate expectedDeliveryDate) {

	    List<Shipment> shipments =
	            shipmentRepository.findByExpectedDeliveryDate(expectedDeliveryDate);

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException(
	                "No shipments found for delivery date : " + expectedDeliveryDate);
	    }

	    return shipments;
	}
}
