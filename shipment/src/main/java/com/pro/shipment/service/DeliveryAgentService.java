package com.pro.shipment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pro.shipment.entity.DeliveryAgent;

@Service
public interface DeliveryAgentService {
	DeliveryAgent createDeliveryAgent(DeliveryAgent deliveryAgent);
	List<DeliveryAgent> getAllDeliveryAgents();
	DeliveryAgent getDeliveryAgentById(Long id);
	DeliveryAgent getDeliveryAgentByPhone(String phone);
	DeliveryAgent getDeliveryAgentByVehicleNo(String vehicleNo);
	List<DeliveryAgent> getDeliveryAgentsByRating(Double rating);
}
