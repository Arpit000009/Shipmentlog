package com.pro.shipment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pro.shipment.entity.PackageEntity;
import com.pro.shipment.entity.Shipment;
import com.pro.shipment.enums.PackageType;

@Service
public interface PackageEntityService {
	
	PackageEntity getPackageById(Long id);
	List<PackageEntity> getPackagesByPackageType(PackageType packageType);
	List<PackageEntity> getAllPackages();
//	List<Shipment> getShipmentsByStatus(ShipmentStatus status);
}
