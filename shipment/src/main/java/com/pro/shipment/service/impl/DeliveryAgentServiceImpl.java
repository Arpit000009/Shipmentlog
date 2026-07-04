package com.pro.shipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.DeliveryAgent;
import com.pro.shipment.enums.AgentAvailability;
import com.pro.shipment.exception.ResourceAlreadyExistsException;
import com.pro.shipment.exception.ResourceNotFoundException;
import com.pro.shipment.repository.DeliveryAgentRepository;
import com.pro.shipment.service.DeliveryAgentService;

@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {
	
	@Autowired
	DeliveryAgentRepository deliveryAgentRepository;
	
	@Override
	public DeliveryAgent createDeliveryAgent(DeliveryAgent deliveryAgent) {

	    if (deliveryAgentRepository.existsByPhone(deliveryAgent.getPhone())) {
	        throw new ResourceAlreadyExistsException("Phone number already exists.");
	    }

	    if (deliveryAgentRepository.existsByVehicleNo(deliveryAgent.getVehicleNo())) {
	        throw new ResourceAlreadyExistsException("Vehicle number already exists.");
	    }

	    return deliveryAgentRepository.save(deliveryAgent);
	}
	
	@Override
	public List<DeliveryAgent> getAllDeliveryAgents() {

	    return deliveryAgentRepository.findAll();
	}
	
	@Override
	public DeliveryAgent getDeliveryAgentById(Long id) {

	    Optional<DeliveryAgent> optionalDeliveryAgent =
	            deliveryAgentRepository.findById(id);

	    if (optionalDeliveryAgent.isPresent()) {

	        return optionalDeliveryAgent.get();

	    } else {

	        throw new ResourceNotFoundException(
	                "Delivery Agent not found with id : " + id);
	    }
	}
	
	@Override
	public DeliveryAgent getDeliveryAgentByPhone(String phone) {

	    DeliveryAgent deliveryAgent = deliveryAgentRepository.findByPhone(phone);

	    if (deliveryAgent != null) {
	        return deliveryAgent;
	    } else {
	        throw new ResourceNotFoundException(
	                "Delivery Agent not found with phone number : " + phone);
	    }
	}
	
	@Override
	public DeliveryAgent getDeliveryAgentByVehicleNo(String vehicleNo) {

	    DeliveryAgent deliveryAgent =
	            deliveryAgentRepository.findByVehicleNo(vehicleNo);

	    if (deliveryAgent != null) {
	        return deliveryAgent;
	    } else {
	        throw new ResourceNotFoundException(
	                "Delivery Agent not found with vehicle number : " + vehicleNo);
	    }
	}
	
	@Override
	public List<DeliveryAgent> getDeliveryAgentsByRating(Double rating) {

	    List<DeliveryAgent> deliveryAgents =
	            deliveryAgentRepository.findByRatingGreaterThan(rating);

	    if (deliveryAgents.isEmpty()) {
	        throw new ResourceNotFoundException(
	                "No delivery agents found with rating greater than " + rating);
	    }

	    return deliveryAgents;
	}
	
	@Override
	public DeliveryAgent updateDeliveryAgent(Long id, DeliveryAgent deliveryAgent) {

	    Optional<DeliveryAgent> optionalDeliveryAgent =
	            deliveryAgentRepository.findById(id);

	    if (optionalDeliveryAgent.isPresent()) {

	        DeliveryAgent existingAgent = optionalDeliveryAgent.get();

	        if (!existingAgent.getPhone().equals(deliveryAgent.getPhone())
	                && deliveryAgentRepository.existsByPhone(deliveryAgent.getPhone())) {

	            throw new ResourceAlreadyExistsException("Phone number already exists.");
	        }

	        if (!existingAgent.getVehicleNo().equals(deliveryAgent.getVehicleNo())
	                && deliveryAgentRepository.existsByVehicleNo(deliveryAgent.getVehicleNo())) {

	            throw new ResourceAlreadyExistsException("Vehicle number already exists.");
	        }

	        existingAgent.setName(deliveryAgent.getName());
	        existingAgent.setPhone(deliveryAgent.getPhone());
	        existingAgent.setVehicleNo(deliveryAgent.getVehicleNo());
	        existingAgent.setAvailabilityStatus(deliveryAgent.getAvailabilityStatus());
	        existingAgent.setRating(deliveryAgent.getRating());

	        return deliveryAgentRepository.save(existingAgent);

	    } else {

	        throw new ResourceNotFoundException(
	                "Delivery Agent not found with id : " + id);
	    }
	}
	
	@Override
	public void deleteDeliveryAgent(Long id) {

	    Optional<DeliveryAgent> optionalDeliveryAgent =
	            deliveryAgentRepository.findById(id);

	    if (optionalDeliveryAgent.isPresent()) {

	        deliveryAgentRepository.deleteById(id);

	    } else {

	        throw new ResourceNotFoundException(
	                "Delivery Agent not found with id : " + id);
	    }
	}
	
	@Override
	public DeliveryAgent updateAvailability(Long id,
	                                        AgentAvailability availabilityStatus) {

	    Optional<DeliveryAgent> optionalDeliveryAgent =
	            deliveryAgentRepository.findById(id);

	    if (optionalDeliveryAgent.isPresent()) {

	        DeliveryAgent deliveryAgent = optionalDeliveryAgent.get();

	        deliveryAgent.setAvailabilityStatus(availabilityStatus);

	        return deliveryAgentRepository.save(deliveryAgent);

	    } else {

	        throw new ResourceNotFoundException(
	                "Delivery Agent not found with id : " + id);
	    }
	}
}
