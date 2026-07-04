package com.pro.shipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.DeliveryAgent;
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
}
