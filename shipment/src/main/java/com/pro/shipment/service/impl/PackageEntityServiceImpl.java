package com.pro.shipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.PackageEntity;
import com.pro.shipment.enums.PackageType;
import com.pro.shipment.exception.ResourceNotFoundException;
import com.pro.shipment.repository.PackageEntityRepository;
import com.pro.shipment.service.PackageEntityService;

@Service
public class PackageEntityServiceImpl implements PackageEntityService{
	
	@Autowired
	PackageEntityRepository packageRepository;
	
	@Override
	public PackageEntity getPackageById(Long id) {

	    Optional<PackageEntity> optionalPackage =
	            packageRepository.findById(id);

	    if (optionalPackage.isPresent()) {

	        return optionalPackage.get();

	    } else {

	        throw new ResourceNotFoundException(
	                "Package not found with id : " + id);
	    }
	}
	
	@Override
	public List<PackageEntity> getPackagesByPackageType(
	        PackageType packageType) {

	    List<PackageEntity> packages =
	            packageRepository.findByPackageType(packageType);

	    if (packages.isEmpty()) {

	        throw new ResourceNotFoundException(
	                "No packages found with package type : "
	                        + packageType);
	    }

	    return packages;
	}
	
}
